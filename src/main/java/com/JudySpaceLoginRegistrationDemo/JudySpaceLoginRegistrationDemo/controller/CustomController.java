package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/custom/")
@RestController
@CrossOrigin("http://localhost:3000")
public class CustomController {
    @GetMapping("login")
    public String customLoginPage() {
        return "http://localhost:3000/login";
    }
}
