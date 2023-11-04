package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvote;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvoteKey;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.BlogUpvoteMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogUpvoteDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.BlogRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.BlogUpvoteRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.UserRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.BlogUpvoteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BlogUpvoteServiceImpl implements BlogUpvoteService {
    private final BlogUpvoteRepository blogUpvoteRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final BlogUpvoteMapper blogUpvoteMapper;

    private String getUserInfor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @Override
    public List<BlogUpvoteDTO> getUpvotedUserListOfBlog(Integer blogId) {
        blogRepository.findById(blogId).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Blog"));
        return blogUpvoteRepository.getBlogUpvoteByBlogId(blogId).stream().map(blogUpvoteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BlogUpvote addBlogUpvote(Integer blogId) {
        Blog existingBlog = blogRepository.findById(blogId).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Blog"));
        Users currUser = userRepository.findByUserName(getUserInfor()).orElse(null);
        BlogUpvote newBlogUpvote = new BlogUpvote(
                new BlogUpvoteKey(blogId, currUser.getUserId()),
                Instant.now(),
                existingBlog,
                currUser
        );
        blogUpvoteRepository.save(newBlogUpvote);
        return newBlogUpvote;
    }

    @Override
    public void delete(Integer blogId) {
        Users currUser = userRepository.findByUserName(getUserInfor()).orElse(null);
        BlogUpvoteKey blogUpvoteKey = new BlogUpvoteKey(blogId, currUser.getUserId());
        BlogUpvote existingBlogUpvote = blogUpvoteRepository.findById(blogUpvoteKey.getBlogId(), blogUpvoteKey.getUserId());
        if (existingBlogUpvote == null){
            throw new EntityNotFoundException("Không tìm thấy");
        }
        blogUpvoteRepository.delete(existingBlogUpvote);
    }
}
