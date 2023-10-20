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
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(new InternetAddress("chuquyson123@gmail.com"));
        helper.setTo(new InternetAddress(user.getEmail()));
        helper.setSubject("Registration Confirmation");
        String verificationLink = "http://localhost:8080/api/auth/confirmRegistration/" + verificationToken;

        helper.setText("<html>\n" +
                " <html  lang='en'>\n" +
                "  <head>\n" +
                "    <meta http-equiv='Content-Type'  content='text/html charset=UTF-8' />\n" +
                "    <title>Day 17/30</title>\n" +
                "    <style>\n" +
                "      body {\n" +

                "        background: #e9e9e9;\n" +
                "      }\n" +
                "      table {\n" +
                "        border-spacing: 0;\n" +
                "      }\n" +
                "      .main {\n" +
                "        margin: 0 auto;\n" +
                "      }\n" +
                "      .content-header {\n" +
                "        background: #e9e9e9;\n" +
                "      }\n" +
                "      .content-margin {\n" +
                "        padding: 20px 50px;\n" +
                "      }\n" +
                "      .content {\n" +
                "        width: 90%;\n" +
                "        margin: 0 auto;\n" +
                "      }\n" +
                "      .content-body {\n" +
                "        background: white;\n" +
                "        border-top: 8px solid #00b0f3;\n" +
                "        border-radius: 5px 5px 0 0;\n" +
                "      }\n" +
                "      .content-footer {\n" +
                "        padding: 20px;\n" +
                "        background: black;\n" +
                "        border-radius: 0 0 5px 5px;\n" +
                "      }\n" +
                "      h1 {\n" +
                "        font-size: 27px;\n" +
                "      }\n" +
                "      .button-container {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "      .button {\n" +
                "        background-color: #00b0f3;\n" +
                "        padding: 15px 32px;\n" +
                "        text-decoration: none;\n" +
                "        display: inline-block;\n" +
                "        border-radius: 5px;\n" +
                "        color: white;\n" +
                "        font-weight: 500;\n" +
                "      }\n" +
                "      .center {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "      a {\n" +
                "        color: black;\n" +
                "        text-decoration: none;\n" +
                "      }\n" +
                "      .copyright {\n" +
                "        background: #e9e9e9;\n" +
                "        font-size: 11px;\n" +
                "        color: grey;\n" +
                "        line-height: 5px;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <body>\n" +
                "      <table role='presentation' class='main'>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table role='presentation' class='content'>\n" +
                "              <tr>\n" +
                "                <td class='content-body content-margin'>\n" +
                "                  <table role='presentation'>\n" +
                "                    <h1>Bạn được mời đến JudySpace!</h1>\n" +
                "                    <p>Hi there,</p>\n" +
                "                    <p>\n" +
                "                      Chào mừng bạn đến với JudySpaceee \uD83E\uDD1E\uD83D\uDE22\uD83E\uDD14, bấm vào link bên\n" +
                "                      dưới để kích hoạt tài khoản của bạn.\n" +
                "                    </p>\n" +
                "                    <p class='button-container'>\n" +
                "                      <a href='" +verificationLink+
                "                    ' class='button'>Kích Hoạt Tài Khoản</a>\n" +
                "                    </p>\n" +
                "                    <p>Rất vui khi gặp bạn,<br />judy</p>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "\n" +
                "              <tr>\n" +
                "                <td class='copyright'>\n" +
                "                  <table role='presentation'>\n" +
                "                    <tr>\n" +
                "                      <td>\n" +
                "                        <p>&copy; JudySpace from Waozuoq<br /></p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </body>\n" +
                "  </body>\n" +
                "</html>\n\n", true);
        mailSender.send(message);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getEmail() + " " + request.getPassword());
        Users user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("The email was not found, consider signing up "));
        System.out.println(user.getUsername() + "  " + user.getPassword());
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
