package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ChangePasswordRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/")
@AllArgsConstructor
public class UserController {
    private UserServiceImpl userService;

    @GetMapping("getAll")
    public ResponseEntity<List<Users>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("testingSecurity")
    private ResponseEntity<Map<String, String>> testing(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = new HashMap<>();
        map.put("userName", auth.getName());
        return ResponseEntity.ok(map);
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(auth.getName());
    }

    @PostMapping("changePassword")
    public ResponseEntity<Map<String, String>> changePassword(@Validated(ChangePasswordRequest.changePassword.class)
                                                                  @RequestBody ChangePasswordRequest request) throws UserPrincipalNotFoundException {
        Map<String, String> map = new HashMap<>();
        map.put("SuccessfulMessage", userService.changePassword(request));
        return ResponseEntity.ok(map);
    }
    @PostMapping("resetPassword")
    public ResponseEntity<Map<String, String>> resetPassword(@Validated(ChangePasswordRequest.resetPassword.class)
                                                    @RequestBody ChangePasswordRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("SuccessfulMessage", userService.resetPassword(request.getEmail()));
        return ResponseEntity.ok(map);
    }
    @ExceptionHandler(UserPrincipalNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)// Nếu validate fail thì trả về 400
    public ResponseEntity<Map<String, String>> handleInvalidUserInfo(UserPrincipalNotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put(HttpStatus.NOT_FOUND.toString()+" Invalid Request", e.getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)// Nếu validate fail thì trả về 400
    public String handleEmailNotFoundException(UsernameNotFoundException e) {
        return "Invalid Request: " + e.getMessage();
    }
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)// Nếu validate fail thì trả về 400
    public String handleInputValidationException(BindException e) {
        String errorMessage = "InvaLid Request: ";
        if (e.getBindingResult().hasErrors()) {
            errorMessage += e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }
        return errorMessage;
    }
}
