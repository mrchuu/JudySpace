package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ChangePasswordRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.UserRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.UserService;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.utilis.RandomStringGenerator;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Users saveNewUser(Users users) {
        if (userRepository.findByEmailNotOptional(users.getEmail()) != null) {
            throw new EntityExistsException("The email has been registered");
        }
        userRepository.save(users);
        return users;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String enableUser(String username) {
        Users existedUser = userRepository.findByUserName(username).orElse(null);
        if (existedUser == null) {
            throw new UsernameNotFoundException("The user with the given Email was not found");
        }
        if (existedUser.isEnabled()) {
            throw new EntityExistsException("The account has already been enabled");
        }
        existedUser.setEnabled(true);
        userRepository.save(existedUser);
        return "Your account has been successfully verified";
    }

    @Override
    public String changePassword(ChangePasswordRequest request) throws UserPrincipalNotFoundException {
        String email = request.getEmail();
        Users users = userRepository.findByEmail(email).orElse(null);
        if (users == null) {
            throw new UserPrincipalNotFoundException("The user with the given Email was not found");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), users.getPassword())) {
            throw new UserPrincipalNotFoundException("The given email and password did not match");
        }
        users.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(users);
        return "Password changed successfully!";
    }

    @Override
    public String resetPassword(String email) throws Exception {
        Users users = userRepository.findByEmail(email).orElse(null);
        if (users == null) {
            throw new UserPrincipalNotFoundException("The user with the given Email was not found");
        }
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        String generatedRandomPassword = randomStringGenerator.generateRandomString(7);
        users.setPassword(passwordEncoder.encode(generatedRandomPassword));
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            message.setFrom(new InternetAddress("chuquyson123@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(users.getEmail()));
            message.setSubject("Reset Password");
            message.setContent("Your password has been reset to: " + generatedRandomPassword + ", you can go to change password to change your password to your liking", "text/html");
            mailSender.send(message);
            userRepository.save(users);
            return "Mật khẩu đã được reset thành công, kiểm tra mail thư mục mail đến hoặc mail rác để nhận mật khẩu mới";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
