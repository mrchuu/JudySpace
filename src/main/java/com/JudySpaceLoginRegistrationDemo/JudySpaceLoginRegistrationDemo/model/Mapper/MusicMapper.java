package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Music;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.MusicRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MusicDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicMapper extends EntityMapper<MusicDTO, Music, MusicRequest>{
}
