package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Comment;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.CommentMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.CommentRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.CommentDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.BlogRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.CommentRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.UserRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private String getUserInfor(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    @Override
    public List<CommentDTO> getRootComments(Integer blogId) {
        blogRepository.findById(blogId).orElseThrow(()->new EntityNotFoundException("Không tìm thấy blog"));
        return commentRepository.getRootComments(blogId).stream().map(commentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCildComments(Integer commentId) {
        commentRepository.findById(commentId).orElseThrow(()->new EntityNotFoundException("Không tìm thấy comment"));
        return commentRepository.getChildComment(commentId).stream().map(commentMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public CommentDTO makeRootComment(CommentRequest commentRequest) {
        Comment newRootComment = commentMapper.toE(commentRequest);
        newRootComment.setPoster(userRepository.findByUserName(getUserInfor()).orElse(null));
        newRootComment.setRoot(true);
        commentRepository.save(newRootComment);
        return commentMapper.toDto(newRootComment);
    }

    @Override
    public CommentDTO makeChildComment(CommentRequest commentRequest) {
        Comment newChildComment = commentMapper.toE(commentRequest);
        newChildComment.setPoster(userRepository.findByUserName(getUserInfor()).orElse(null));
        commentRepository.save(newChildComment);
        return commentMapper.toDto(newChildComment);
    }

    @Override
    @Transactional
    public CommentDTO updateComment(CommentRequest updateCommentRequest) throws IllegalAccessException {
        Comment existingComment = commentRepository.findById(updateCommentRequest.getCommentId()).orElseThrow(()-> new EntityNotFoundException("Không tìm thấy Blog"));
        Users inSessionUser = userRepository.findByUserName(getUserInfor()).orElse(null);
        if (inSessionUser.getUserId() != existingComment.getPoster().getUserId()){
            throw new IllegalAccessException("Bạn không thể chỉnh sửa comment của người dùng khác");
        }
        existingComment = commentMapper.toE(updateCommentRequest);
        existingComment.setUpdateDate(Instant.now());
        commentRepository.save(existingComment);
        return commentMapper.toDto(existingComment);
    }

    @Override
    public void delete(Integer commentId) {
        Comment existingComment = commentRepository.findById(commentId).orElseThrow(()->new EntityNotFoundException("Không tìm thấy comment"));
        existingComment.setContent("Comment đã được thu hồi bởi ngươi viết");
        existingComment.setDeleted(true);
        existingComment.setDeleteDate(Instant.now());
        commentRepository.save(existingComment);
    }

}
