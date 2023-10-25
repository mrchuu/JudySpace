package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.BlogService;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.BlogServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/blog/")
public class BlogController {
    private BlogService blogService;
    @GetMapping("getAll")
    public ResponseEntity<List<BlogDTO>> getAll(){
        return ResponseEntity.ok(blogService.getAll());
    }
}
