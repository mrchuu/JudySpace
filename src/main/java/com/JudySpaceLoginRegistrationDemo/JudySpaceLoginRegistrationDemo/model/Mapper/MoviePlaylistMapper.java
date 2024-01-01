package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.MoviePlayList;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.MoviePlaylistRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MoviePlayListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoviePlaylistMapper extends EntityMapper<MoviePlayListDTO, MoviePlayList, MoviePlaylistRequest>{
}
