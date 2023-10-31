package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller.Auth;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Provider;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.RoleRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.UserRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.UserServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.utilis.JwtService;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.utilis.RandomStringGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class CustomSuccessOauth2AuthHandler implements AuthenticationSuccessHandler {
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
                if(!existingUser.isEnabled()){
                    Map<String, String> map = new HashMap<>();
                    map.put("404 BAD REQUEST", "Tài khoản của bạn chưa được kích hoạt, vui lòng kiểm tra thư mục thư rác hoặc thư đến");
                    response.setStatus(HttpStatus.BAD_REQUEST.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), map);
                }
                existingUser.setLastVisit(ZonedDateTime.now());
                userRepository.save(existingUser);
                String accessToken = jwtService.generateAccessToken(existingUser);
                String refreshToken = jwtService.generateRefreshToken(existingUser);
                response.sendRedirect("http://localhost:3000/oauth2proceed/" + accessToken + "/" + refreshToken);
            } else {
                try {
                    RandomStringGenerator rand = new RandomStringGenerator();
                    String generatedRandomPassword = rand.generateRandomString(7);
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
                    MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                    helper.setFrom(new InternetAddress("chuquyson123@gmail.com"));
                    helper.setTo(new InternetAddress(user.getEmail()));
                    helper.setSubject("Registration Confirmation");
                    String verificationLink = "http://localhost:8080/api/auth/confirmRegistration/" + verificationToken;

                    helper.setText("<html>\n" +
                            " <html>\n" +
                            "  <head>\n" +
                            "    <meta charset='UTF-8' />\n" +
                            "    <title>Day 17/30</title>\n" +
                            "    <style>\n" +
                            "      body {\n" +
                            "        font-family: Roboto, sans-serif;\n" +
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
                    response.sendRedirect("http://localhost:3000/login");
                    //response
                } catch (Exception e) {
                    Map<String, String> map = new HashMap<>();
                    map.put(e.getLocalizedMessage(), e.getMessage());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), map);
                }


            }
        }

    }
}
