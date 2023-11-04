package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/custom/")
@RestController
public class CustomController {
    @GetMapping("login")
    public String customLoginPage() {
        return "http://localhost:3000/login";
    }
}
