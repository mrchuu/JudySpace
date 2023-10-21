package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.dto.AuthenticationResponse;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.AuthenticationRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ChangePasswordRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.RegisterRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.AuthenticationService;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
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
    public ResponseEntity<ResponseMessage> register(
            @Valid
            @RequestBody RegisterRequest request
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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public ResponseEntity<ResponseMessage> handleCommonException(Exception e) {
        ResponseMessage rm = new ResponseMessage("Hành động không hợp lệ", e.getMessage());
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT.value()).body(rm);
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> handleCommonException(EntityExistsException e) {
        ResponseMessage rm = new ResponseMessage("Hành động không hợp lệ", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(rm);
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)// Nếu validate fail thì trả về 400
    public ResponseEntity<ResponseMessage> handleCommonException(BadCredentialsException e) {
        ResponseMessage rm = new ResponseMessage("Không tìm thấy", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(rm);
    }
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)// Nếu validate fail thì trả về 400
    public ResponseEntity<ResponseMessage> handleInputValidationException(BindException e) {
        ResponseMessage rm = new ResponseMessage("Hành động không hợp lệ", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(rm);
    }

}
