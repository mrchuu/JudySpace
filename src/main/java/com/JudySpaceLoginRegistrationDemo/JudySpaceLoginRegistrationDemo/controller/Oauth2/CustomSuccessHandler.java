package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller.Oauth2;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Provider;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.dto.AuthenticationResponse;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.AuthenticationRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.RoleRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.UserRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.AuthenticationService;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.UserServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.utilis.JwtService;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Random;

@Component
@AllArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private final UserServiceImpl userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private JavaMailSender mailSender;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User userDetails = (DefaultOAuth2User) authentication.getPrincipal();
            String userName = userDetails.getName();
            Users existingUser = userService.findByEmail(userDetails.getAttribute("email"));
            if (existingUser != null) {
                existingUser.setLastVisit(ZonedDateTime.now());
                userRepository.save(existingUser);
                String accessToken = jwtService.generateAccessToken(existingUser);
                String refreshToken = jwtService.generateRefreshToken(existingUser);
                response.sendRedirect("http://localhost:3000/oauth2proceed/" + accessToken + "/" + refreshToken);
            } else {
                try {

                }catch (Exce)
                byte[] array = new byte[7]; // length is bounded by 7
                new Random().nextBytes(array);
                String generatedRandomPassword = new String(array, StandardCharsets.UTF_8);
                Users user = Users.builder()
                        .userName(userDetails.getAttribute("name"))
                        .email(userDetails.getAttribute("email"))
                        .role(roleRepository.findById(2).orElse(null))
                        .avatarLink("https://ibb.co/fGgTyk1?fbclid=IwAR1h4B6lrpx1l2zFEfJMXheag2Lt3oAgooi6OlVQTSqsKdDtrgiKdlPafGI")
                        .password(passwordEncoder.encode(generatedRandomPassword))
                        .isBanned(false)
                        .providerId(Provider.google.name())
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
        }

    }
}
