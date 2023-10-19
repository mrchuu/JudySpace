package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ChangePasswordRequest;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface UserService {
    public Users findByEmail(String email);

    public Users saveNewUser(Users users);

    public List<Users> getAllUsers();

    String enableUser(String username);

    String changePassword(ChangePasswordRequest request) throws UserPrincipalNotFoundException;

    String resetPassword(String email) throws Exception;
}
