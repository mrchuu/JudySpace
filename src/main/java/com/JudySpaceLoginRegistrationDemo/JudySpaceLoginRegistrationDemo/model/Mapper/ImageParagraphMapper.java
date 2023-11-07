package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.ImageParagraph;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ImageParagraphRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ImageParagraphDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageParagraphMapper extends EntityMapper<ImageParagraphDTO, ImageParagraph, ImageParagraphRequest> {
}
