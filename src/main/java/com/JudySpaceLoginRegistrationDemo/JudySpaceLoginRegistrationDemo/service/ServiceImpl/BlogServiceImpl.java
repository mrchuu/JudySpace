package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.ImageParagraph;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.BlogMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.ParagraphMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Paragraph;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.BlogRequest;
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
        Page<BlogDTO> res = null;
        if (!auth.getName().equalsIgnoreCase("AnonymousUser")) {
            Users currentUser = userRepository.findByUserName(auth.getName()).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng"));
            res = blogRepository.getBlogsByPage(blogPageRequest.getSearchName(), blogPageRequest.getTagId(), blogPageRequest.getSortType(), blogPageRequest.getCategoryId(), blogPageRequest.getMovieCategories(), blogPageRequest.getMovieCategories().size(), pageable).map(
                    blog -> {
                        BlogDTO blogDTO = blogMapper.toDtoWithCustomInfo(blog);
                        blog.getUpvotedUsers().stream().filter(user -> user.getUser().getUserId() == currentUser.getUserId())
                                .forEach(blogUpvote -> blogDTO.setUpvotedByCurrentUser(true));
                        return blogDTO;
                    }
            );
        } else {
            res = blogRepository.getBlogsByPage(blogPageRequest.getSearchName(), blogPageRequest.getTagId(), blogPageRequest.getSortType(), blogPageRequest.getCategoryId(), blogPageRequest.getMovieCategories(), blogPageRequest.getMovieCategories().size(), pageable).map(blogMapper::toDtoWithCustomInfo);
        }

        return res;
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

    @Override
    public BlogDTO addBlog(BlogRequest blogRequest) {
        Blog newBlog = blogMapper.toE(blogRequest);
        for (Paragraph newParagraph: newBlog.getParagraphs()){
            for(ImageParagraph image: newParagraph.getImageParagraphs()){
                image.setParagraph(newParagraph);
            }
            newParagraph.setBlog(newBlog);
        }
        blogRepository.save(newBlog);
        return blogMapper.toDtoWithCustomInfo(newBlog);
    }

    @Override
    public BlogDTO updateBlog(BlogRequest blogRequest) {
        Blog existingBlog = blogRepository.findById(blogRequest.getBlogId()).orElseThrow(()->new EntityNotFoundException("Không tìm thấy Blog"));
        existingBlog = blogMapper.toE(blogRequest);
        blogRepository.save(existingBlog);
        return blogMapper.toDtoWithCustomInfo(existingBlog);
    }

}
