package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;

import java.util.List;

public interface UserService {
    public Users findByEmail(String email);

    public Users saveNewUser(Users users);

    public List<Users> getAllUsers();

    String enableUser(String username);
}
