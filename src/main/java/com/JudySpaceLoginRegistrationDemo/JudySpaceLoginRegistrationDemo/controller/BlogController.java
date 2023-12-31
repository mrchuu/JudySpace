package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Paragraph;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.BlogRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.searchRequest.BlogPageRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ParagraphDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.BlogService;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl.BlogServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/blog/")
public class BlogController {
    private BlogService blogService;
//    @PreAuthorize("hasAnyAuthority('ROLE_Judy')")
    @GetMapping("getAll")
    public ResponseEntity<List<BlogDTO>> getAll(){
        return ResponseEntity.ok(blogService.getAll());
    }
    @PostMapping("getBlogsPaginated")
    public Page<BlogDTO> getBlogsPaginated(@RequestBody BlogPageRequest blogPageRequest){
        return blogService.getBlogsPaginated(blogPageRequest);
    }
    @GetMapping("getBlogContent/{blogId}")
    public ResponseEntity<List<ParagraphDTO>> getBlogContent(@PathVariable("blogId") Integer blogId){
        return ResponseEntity.ok(blogService.getBlogContent(blogId));
    }
    @GetMapping("getBlogDetail/{blogId}")
    public ResponseEntity<BlogDTO> getBlogDetail(@PathVariable("blogId") Integer blogId){
        return ResponseEntity.ok(blogService.getBlogDetail(blogId));
    }
    @PostMapping("addBlog")
    public ResponseEntity<BlogDTO> addBlog(@RequestBody BlogRequest blogRequest){
        return ResponseEntity.ok(blogService.addBlog(blogRequest));
    }
    @PostMapping("updateBlog")
    public ResponseEntity<BlogDTO> updateBlog(@RequestBody BlogRequest blogRequest){
        return ResponseEntity.ok(blogService.updateBlog(blogRequest));
    }
}
