package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        throw new RuntimeException("aaaaaaaaaaaaaa");
//        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("testingSecurity")
    private ResponseEntity<Map<String, String>> testing(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = new HashMap<>();
        map.put("userName", auth.getName());
        return ResponseEntity.ok(map);
    }

}
