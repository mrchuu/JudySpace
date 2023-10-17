package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Provider;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Role;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.dto.AuthenticationResponse;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.AuthenticationRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.RegisterRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.RoleRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.UserRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.utilis.JwtService;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;


@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private JavaMailSender mailSender;

    public void register(RegisterRequest request) throws Exception {
        Users user = Users.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .role(roleRepository.findById(2).orElse(null))
                .avatarLink(request.getAvatarLink())
                .password(passwordEncoder.encode(request.getPassword()))
                .isBanned(false)
                .providerId(Provider.local.name())
                .isEnabled(false)
                .build();
        userService.saveNewUser(user);
        String verificationToken = jwtService.generateVerificationToken(user);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        message.setFrom(new InternetAddress("chuquyson123@gmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        message.setSubject("Registration Confirmation");
        String verificationLink = "http://localhost:8080/api/auth/confirmRegistration/" + verificationToken;
        message.setContent("Please click the link below to confirm your registration\n" +
                "<a style='text-decoration: none; color: red;' href='" + verificationLink + "'>Click here</a>", "text/html");
        mailSender.send(message);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getEmail()+ " "+request.getPassword());
        Users user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("The email was not found, consider signing up "));
        System.out.println(user.getUsername()+"  "+user.getPassword());
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        user.setLastVisit(ZonedDateTime.now());
        userRepository.save(user);
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String confirmRegistration(
            String verificationToken) throws Exception {
        String message = "";
        try {
            String username = jwtService.extractUsername(verificationToken);
            UserDetails users = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(verificationToken,
                    users)) {
                message = userService.enableUser(username);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return message;
    }
}
