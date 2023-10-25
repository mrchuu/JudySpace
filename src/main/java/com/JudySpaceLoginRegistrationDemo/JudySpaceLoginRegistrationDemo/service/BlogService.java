package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.searchRequest.BlogPageRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogUpvoteDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
    public List<BlogDTO> getAll();

    public Page<BlogDTO> getBlogsPaginated(BlogPageRequest blogPageRequest);
}
