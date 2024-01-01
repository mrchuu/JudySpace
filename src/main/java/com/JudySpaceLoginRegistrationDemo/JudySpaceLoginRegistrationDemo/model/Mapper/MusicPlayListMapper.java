package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.EntityMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.MusicPlayList;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.MusicPlaylistRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MusicPlayListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicPlayListMapper extends EntityMapper<MusicPlayListDTO, MusicPlayList, MusicPlaylistRequest> {
}
