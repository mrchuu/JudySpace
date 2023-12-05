package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.ChildImages;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ChildImageRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ChildImageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChildImageMapper extends EntityMapper<ChildImageDTO, ChildImages, ChildImageRequest> {
}
