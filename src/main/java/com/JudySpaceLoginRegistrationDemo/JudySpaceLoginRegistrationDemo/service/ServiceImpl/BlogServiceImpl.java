package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.BlogMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.searchRequest.BlogPageRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogUpvoteDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.BlogRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;
    private BlogMapper blogMapper;
    @Override
    public List<BlogDTO> getAll() {
        return blogRepository.findAll().stream().map(blogMapper::toDtoWithCustomInfo).collect(Collectors.toList());
    }

    @Override
    public Page<BlogDTO> getBlogsPaginated(BlogPageRequest blogPageRequest) {
        Pageable pageable = PageRequest.of(0, blogPageRequest.getPageSize()*(blogPageRequest.getPageIndex()+1));
        return blogRepository.getBlogsByPage(blogPageRequest.getSearchName(), blogPageRequest.getTagId(), blogPageRequest.getSortType(),pageable).map(blogMapper::toDtoWithCustomInfo);
    }

}
