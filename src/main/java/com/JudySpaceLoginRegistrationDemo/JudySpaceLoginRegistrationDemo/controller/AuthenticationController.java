package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.dto.AuthenticationResponse;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.AuthenticationRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.UserRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.AuthenticationServiceImpl;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;
    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(
            @Valid
            @RequestBody UserRequest request
    ) throws Exception {
        ResponseMessage rm = new ResponseMessage("Đăng kí thành công", authenticationService.register(request));
        return ResponseEntity.ok(rm);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(
            @Valid
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return response;
    }
    @PostMapping("/refreshToken")
    public ResponseEntity<ResponseMessage> refreshAccessToken(@RequestHeader(name = "Authorization") String refreshToken){
        String refreshedAccessToken = authenticationService.refreshToken(refreshToken.substring(7));
        return ResponseEntity.ok(new ResponseMessage("The accessToken was successfully refreshed", refreshedAccessToken));
    }

    @GetMapping("/confirmRegistration/{verificationToken}")
    public ResponseEntity<ResponseMessage> confirmRegistration(@PathVariable("verificationToken") String verificationToken) throws Exception {
        ResponseMessage rm = new ResponseMessage("Kích hoạt tài khoản thành công", authenticationService.confirmRegistration(verificationToken));
        return ResponseEntity.ok(rm);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)// Nếu validate fail thì trả về 400
    public ResponseEntity<ResponseMessage> handleCommonException(UsernameNotFoundException e) {
        // Trả về message của lỗi đầu tiên
        ResponseMessage rm = new ResponseMessage("Bad Credentials", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(rm);
    }



}
