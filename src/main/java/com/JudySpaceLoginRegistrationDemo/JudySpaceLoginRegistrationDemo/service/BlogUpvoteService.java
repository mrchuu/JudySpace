package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvote;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.BlogUpvoteRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogUpvoteDTO;

import java.util.List;

public interface BlogUpvoteService {
    public List<BlogUpvoteDTO> getUpvotedUserListOfBlog(Integer blogId);
    BlogUpvoteDTO addBlogUpvote(Integer blogId);

    void delete(Integer blogId);
}
