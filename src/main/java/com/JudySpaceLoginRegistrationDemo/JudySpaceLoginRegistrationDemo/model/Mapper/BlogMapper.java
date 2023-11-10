package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.BlogRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    @Mapping(target = "upvoteUserSetSize",
            expression = "java(b.getUpvotedUsers()!=null?b.getUpvotedUsers().size():0)")
    @Mapping(target = "commentSetSize",
            expression = "java(b.getComments()!=null?b.getComments().stream().filter(comment -> comment.isRoot()).map(comment->comment.getNumberOfChild(comment)).mapToInt(Integer::intValue).sum():0)")
    @Mapping(target = "upvotedByCurrentUser", expression = "java(false)")
    BlogDTO toDtoWithCustomInfo(Blog b);
    Blog toE(BlogRequest blogRequest);
}
