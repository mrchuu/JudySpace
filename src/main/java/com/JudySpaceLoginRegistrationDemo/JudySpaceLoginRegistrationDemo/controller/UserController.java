package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ChangePasswordRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.UserDTO;
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
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("testingSecurity")
    private ResponseEntity<Map<String, String>> testing(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = new HashMap<>();
        map.put("userName", auth.getName());
        return ResponseEntity.ok(map);
    }

    @PostMapping("changePassword")
    public ResponseEntity<ResponseMessage> changePassword(@Validated(ChangePasswordRequest.changePassword.class)
                                                          @RequestBody ChangePasswordRequest request) throws UserPrincipalNotFoundException {
        ResponseMessage rm = new ResponseMessage("Thành công", userService.changePassword(request));
        return ResponseEntity.ok(rm);
    }

    @PostMapping("resetPassword")
    public ResponseEntity<ResponseMessage> resetPassword(@Validated(ChangePasswordRequest.resetPassword.class)
                                                         @RequestBody ChangePasswordRequest request) throws Exception {
        ResponseMessage rm = new ResponseMessage("Thành công", userService.resetPassword(request.getEmail()));
        return ResponseEntity.ok(rm);
    }

    @ExceptionHandler(UserPrincipalNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessage> handleInvalidUserInfo(UserPrincipalNotFoundException e) {
        ResponseMessage rm = new ResponseMessage("Hành động không hợp lệ", e.getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rm);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessage> handleEmailNotFoundException(UsernameNotFoundException e) {
        ResponseMessage rm = new ResponseMessage("Hành động không hợp lệ", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rm);
    }

}
