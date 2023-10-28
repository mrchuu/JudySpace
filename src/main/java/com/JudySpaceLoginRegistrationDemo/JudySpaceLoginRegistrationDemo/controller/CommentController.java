package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Comment;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.CommentDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comment/")
public class CommentController {
    private CommentService commentService;
    @PostMapping("getRootComments/{blogId}")
    public ResponseEntity<List<CommentDTO>> getRootComment(@PathVariable(name = "blogId") Integer blogId){
        return ResponseEntity.ok(commentService.getRootComments(blogId));
    }
    @PostMapping("getChildComments/{commentId}")
    public ResponseEntity<List<CommentDTO>> getChildComment(@PathVariable(name = "commentId") Integer commentId){
        return ResponseEntity.ok(commentService.getCildComments(commentId));
    }
}
