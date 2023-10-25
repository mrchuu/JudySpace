package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogTag;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.BlogTagRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.BlogTagDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogTagMapper extends EntityMapper<BlogTagDTO, BlogTag, BlogTagRequest> {
}
