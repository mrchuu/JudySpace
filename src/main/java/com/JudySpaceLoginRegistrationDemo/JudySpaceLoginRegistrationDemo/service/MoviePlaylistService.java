package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MoviePlayListDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface MoviePlaylistService {
    List<MoviePlayListDTO> getAllPlayList();

    void addMovieToPlayList(Integer playListId, Integer blogId);
}
