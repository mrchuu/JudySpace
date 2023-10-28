package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Comment;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.CommentDTO;

import java.util.List;

public interface CommentService {
    public List<CommentDTO> getRootComments(Integer blogId);
    public List<CommentDTO> getCildComments(Integer commentId);
}