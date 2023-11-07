package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvote;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.BlogUpvoteRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogUpvoteDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.BlogUpvoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/blogUpvote/")
public class BlogUpvoteController {
    private final BlogUpvoteService blogUpvoteService;
    @GetMapping("getUpvotedUserListOfBlog/{blogId}")
    public ResponseEntity<List<BlogUpvoteDTO>> getUpvotedUserListOfBlog(@PathVariable("blogId") Integer blogId){
        return ResponseEntity.ok(blogUpvoteService.getUpvotedUserListOfBlog(blogId));
    }
    @PostMapping("add/{blogId}")
    public ResponseEntity<BlogUpvoteDTO> addUpvote(@PathVariable("blogId") Integer blogId){
        return ResponseEntity.ok(blogUpvoteService.addBlogUpvote(blogId));
    }
    @DeleteMapping("delete/{blogId}")
    public ResponseMessage deleteUpvote(@PathVariable("blogId") Integer blogId){
        blogUpvoteService.delete(blogId);
        return new ResponseMessage("200", "Thành công");
    }
}
