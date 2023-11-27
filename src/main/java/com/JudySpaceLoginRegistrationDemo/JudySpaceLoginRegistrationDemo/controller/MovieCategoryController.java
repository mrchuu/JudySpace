package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MovieCategoryDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.MovieCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movieCategoryController/")
public class MovieCategoryController {
    private MovieCategoryService movieCategoryService;
    @GetMapping("getAll")
    public ResponseEntity<List<MovieCategoryDTO>> getAll(){
        return ResponseEntity.ok(movieCategoryService.getAll());
    }
}
