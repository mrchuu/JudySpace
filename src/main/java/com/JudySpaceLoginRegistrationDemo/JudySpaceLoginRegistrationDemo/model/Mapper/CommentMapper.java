package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Comment;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.CommentRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "upvoteNumber", expression = "java(c.getUpvotedUsers()!=null?c.getUpvotedUsers().size():0)")
    @Mapping(target = "childCommentNumber", expression = "java(c.getChildComments()!=null?c.getChildComments().stream().map(child->child.getNumberOfChild(child)).mapToInt(Integer::intValue).sum():0)")
    public CommentDTO toDto(Comment c);
    public Comment toE(CommentRequest request);
}
