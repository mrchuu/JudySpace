package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.UserRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.UserService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public Users findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }
    @Override
    public Users saveNewUser(Users users){
        if(userRepository.findByEmailNotOptional(users.getEmail())!=null){
            throw new EntityExistsException("The email has been registered");
        }
        userRepository.save(users);
        return users;
    }
    @Override
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public String enableUser(String username) {
        Users existedUser = userRepository.findByUserName(username).orElse(null);
        if(existedUser==null){
            throw new UsernameNotFoundException("The user with the given Email was not found");
        }
        if (existedUser.isEnabled()){
            throw new EntityExistsException("The account has already been enabled");
        }
        existedUser.setEnabled(true);
        userRepository.save(existedUser);
        return "Your account has been successfully verified";
    }


}
