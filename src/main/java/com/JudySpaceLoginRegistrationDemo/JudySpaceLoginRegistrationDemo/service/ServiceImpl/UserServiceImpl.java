package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.UserMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ChangePasswordRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.UserDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.UserRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.UserService;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.utilis.RandomStringGenerator;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final UserMapper userMapper;

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Users saveNewUser(Users users) {
        if (userRepository.findByEmailNotOptional(users.getEmail()) != null) {
            throw new EntityExistsException("Email đã được đăng kí");
        }
        userRepository.save(users);
        return users;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public String enableUser(String username) {
        Users existedUser = userRepository.findByUserName(username).orElse(null);
        if (existedUser == null) {
            throw new UsernameNotFoundException("Không tìm thấy Email");
        }
        if (existedUser.isEnabled()) {
            throw new EntityExistsException("Tài khoản đã được kích hoạt");
        }
        existedUser.setEnabled(true);
        userRepository.save(existedUser);
        return "Tài khoản của bạn đã được kích hoạt thành công";
    }

    @Override
    public String changePassword(ChangePasswordRequest request) throws UserPrincipalNotFoundException {
        String email = request.getEmail();
        Users users = userRepository.findByEmail(email).orElse(null);

        if (users == null) {
            throw new UserPrincipalNotFoundException("Không tìm thấy Email");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), users.getPassword())) {
            throw new UserPrincipalNotFoundException("Email và mật khẩu không trùng khớp");
        }

        users.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(users);
        return "Đổi mật khẩu thành công";
    }

    @Override
    public String resetPassword(String email) throws Exception {
        Users users = userRepository.findByEmail(email).orElse(null);
        if (users == null) {
            throw new UserPrincipalNotFoundException("Không tìm thấy Email");
        }
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        String generatedRandomPassword = randomStringGenerator.generateRandomString(7);
        users.setPassword(passwordEncoder.encode(generatedRandomPassword));
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            message.setFrom(new InternetAddress("chuquyson123@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(users.getEmail()));
            message.setSubject("Reset Mật khẩu");
            message.setContent("Mật khẩu của bạn đã được reset về: " + generatedRandomPassword + ", ", "text/html");
            mailSender.send(message);
            userRepository.save(users);
            return "Mật khẩu đã được reset thành công, kiểm tra mail thư mục mail đến hoặc mail rác để nhận mật khẩu mới";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UserDTO getCurrentUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Users currentUser = userRepository.findByUserName(userName).orElseThrow(()->new EntityNotFoundException("Không tìm thấy tên đăng nhập"));
        return userMapper.toDto(currentUser);
    }
}
