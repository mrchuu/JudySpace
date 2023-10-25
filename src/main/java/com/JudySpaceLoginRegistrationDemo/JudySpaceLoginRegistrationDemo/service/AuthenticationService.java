package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.dto.AuthenticationResponse;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.AuthenticationRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.UserRequest;

public interface AuthenticationService {
    public String register(UserRequest request) throws Exception;

    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public String confirmRegistration(String verificationToken) throws Exception;
}
