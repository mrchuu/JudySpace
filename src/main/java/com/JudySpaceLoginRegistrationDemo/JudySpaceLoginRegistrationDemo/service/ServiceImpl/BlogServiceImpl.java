package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.BlogMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.ParagraphMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Paragraph;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.searchRequest.BlogPageRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogUpvoteDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ParagraphDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.BlogRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.UserRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.BlogService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;
    private UserRepository userRepository;
    private BlogMapper blogMapper;
    private ParagraphMapper paragraphMapper;

    @Override
    public List<BlogDTO> getAll() {
        return blogRepository.findAll().stream().map(blogMapper::toDtoWithCustomInfo).collect(Collectors.toList());
    }

    @Override
    public Page<BlogDTO> getBlogsPaginated(BlogPageRequest blogPageRequest) {
        Pageable pageable = PageRequest.of(0, blogPageRequest.getPageSize() * (blogPageRequest.getPageIndex() + 1));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("AnonymousUser")) {
            Users currentUser = userRepository.findByUserName(auth.getName()).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng"));
            return blogRepository.getBlogsByPage(blogPageRequest.getSearchName(), blogPageRequest.getTagId(), blogPageRequest.getSortType(), pageable).map(
                    blog -> {
                        BlogDTO blogDTO = blogMapper.toDtoWithCustomInfo(blog);
                        blog.getUpvotedUsers().stream().filter(user -> user.getUser().getUserId() == currentUser.getUserId())
                                .forEach(blogUpvote -> blogDTO.setUpvotedByCurrentUser(true));
                        return blogDTO;
                    }
            );
        } else {
            return blogRepository.getBlogsByPage(blogPageRequest.getSearchName(), blogPageRequest.getTagId(), blogPageRequest.getSortType(), pageable).map(blogMapper::toDtoWithCustomInfo);
        }
    }

    @Override
    public List<ParagraphDTO> getBlogContent(Integer blogId) {
        blogRepository.findById(blogId).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Blog"));
        return blogRepository.getBlogDetails(blogId).stream().map(paragraphMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BlogDTO getBlogDetail(Integer blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Blog"));
        return blogMapper.toDtoWithCustomInfo(blog);
    }

}
