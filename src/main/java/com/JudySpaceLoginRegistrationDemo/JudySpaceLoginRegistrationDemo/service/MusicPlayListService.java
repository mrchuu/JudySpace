package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.MusicPlayList;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.MusicPlaylistRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MusicDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MusicPlayListDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;

import java.util.List;

public interface MusicPlayListService {
    List<MusicPlayListDTO> getAllPlayList();
    List<MusicDTO> getAllSongOfPlayList(Integer playListId);
    ResponseMessage addPlayList(MusicPlaylistRequest playListRequest);

    ResponseMessage addOrRemoveFromPlayList(Integer songId, Integer playListId);
}
