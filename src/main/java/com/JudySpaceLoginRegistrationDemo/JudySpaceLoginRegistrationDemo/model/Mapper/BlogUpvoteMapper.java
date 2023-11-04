package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvote;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.BlogUpvoteRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogUpvoteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogUpvoteMapper extends EntityMapper<BlogUpvoteDTO, BlogUpvote, BlogUpvoteRequest>{

}
