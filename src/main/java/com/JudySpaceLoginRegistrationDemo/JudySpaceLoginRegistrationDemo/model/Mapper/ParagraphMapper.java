package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Paragraph;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ParagraphRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ParagraphDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParagraphMapper extends EntityMapper<ParagraphDTO, Paragraph, ParagraphRequest> {
}
