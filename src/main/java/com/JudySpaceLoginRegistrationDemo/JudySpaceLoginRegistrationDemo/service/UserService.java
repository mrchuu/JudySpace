package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ChangePasswordRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.UserDTO;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface UserService {
    public Users findByEmail(String email);

    public Users saveNewUser(Users users);

    public List<UserDTO> getAllUsers();

    String enableUser(String username);

    String changePassword(ChangePasswordRequest request) throws UserPrincipalNotFoundException;

    String resetPassword(String email) throws Exception;
}
