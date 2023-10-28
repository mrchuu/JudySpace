package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Comment;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.CommentMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.CommentDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.CommentRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    @Override
    public List<CommentDTO> getRootComments(Integer blogId) {
        return commentRepository.getRootComments(blogId).stream().map(commentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCildComments(Integer commentId) {
        return commentRepository.getChildComment(commentId).stream().map(commentMapper::toDto).collect(Collectors.toList());
    }
}
