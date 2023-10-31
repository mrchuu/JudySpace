package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Comment;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.CommentRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.CommentDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("makeRootComment")
    public ResponseEntity<CommentDTO> makeRootComment(@Validated(CommentRequest.AddRootRequest.class) @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok(commentService.makeRootComment(commentRequest));
    }
    @PostMapping("makeChildComment")
    public ResponseEntity<CommentDTO> makeChildComment(@Validated(CommentRequest.AddChildrenRequest.class) @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok(commentService.makeChildComment(commentRequest));
    }
}
