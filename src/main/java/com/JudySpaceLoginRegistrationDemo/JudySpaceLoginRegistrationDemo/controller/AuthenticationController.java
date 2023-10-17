package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.dto.AuthenticationResponse;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.AuthenticationRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.RegisterRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.AuthenticationService;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @RequestBody RegisterRequest request
    ) throws Exception {
        authenticationService.register(request);
        HashMap<String, String> map = new HashMap<>();
        map.put("succcessfulRegisttration", "The registration has been successful, please go to your Gmail to activate your account within an hour");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        System.out.println(response.getAccessToken() + "\n" + response.getRefreshToken());
        return response;
    }

    @GetMapping("/confirmRegistration/{verificationToken}")
    public String confirmRegistration(@PathVariable("verificationToken") String verificationToken) throws Exception {
        return authenticationService.confirmRegistration(verificationToken);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)// Nếu validate fail thì trả về 400
    public String handleCommonException(UsernameNotFoundException e) {
        // Trả về message của lỗi đầu tiên
        String errorMessage = "Invalid Request: " + e.getMessage();
        return errorMessage;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public String handleCommonException(Exception e) {
        String errorMessage = "Invalid Request: " + e.getMessage();
        return errorMessage;
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)// Nếu validate fail thì trả về 400
    public String handleCommonException(EntityExistsException e) {
        // Trả về message của lỗi đầu tiên
        String errorMessage = "Invalid Request: " + e.getMessage();
        return errorMessage;
    }

}
