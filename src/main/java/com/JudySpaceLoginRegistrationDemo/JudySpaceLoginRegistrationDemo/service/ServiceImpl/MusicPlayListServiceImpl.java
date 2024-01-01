package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.MusicMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Music;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.MusicPlayList;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.MusicPlayListMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.MusicPlaylistRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MusicDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MusicPlayListDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.MusicPlayListRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.MusicRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.MusicPlayListService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MusicPlayListServiceImpl implements MusicPlayListService {
    private MusicPlayListRepository musicPlayListRepository;
    private MusicPlayListMapper musicPlayListMapper;
    private MusicMapper musicMapper;
    private MusicRepository musicRepository;
    @Override
    public List<MusicPlayListDTO> getAllPlayList() {
        return musicPlayListRepository.findAll().stream().map(musicPlayListMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MusicDTO> getAllSongOfPlayList(Integer playListId) {
        MusicPlayList playList = musicPlayListRepository.findById(playListId).orElseThrow(()->new EntityNotFoundException("Không tìm thấy PlayList"));
        return playList.getSongsBelongedTo().stream().map(musicMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public ResponseMessage addPlayList(MusicPlaylistRequest playListRequest) {
        MusicPlayList newPlayList = musicPlayListMapper.toE(playListRequest);
        musicPlayListRepository.save(newPlayList);
        return new ResponseMessage("Thành công", "Danh sách phát mới đã được tạo");
    }

    @Override
    public ResponseMessage addOrRemoveFromPlayList(Integer songId, Integer playListId) {
        Music song = musicRepository.findById(songId).orElseThrow(()->new EntityNotFoundException("Không tìm thấy bài hát"));
        MusicPlayList playlist = musicPlayListRepository.findById(playListId).orElseThrow(()->new EntityNotFoundException("Không tìm thấy danh sách phát"));
        if(playlist.getSongsBelongedTo().contains(song)){
            song.getPlayListBelongedTo().remove(playlist);
            playlist.getSongsBelongedTo().remove(song);
            musicRepository.save(song);
            musicPlayListRepository.save(playlist);
            return new ResponseMessage("Thành công", "Bài hát đã được xoá khỏi PlayList");
        }
        song.getPlayListBelongedTo().add(playlist);
        playlist.getSongsBelongedTo().add(song);
        musicRepository.save(song);
        musicPlayListRepository.save(playlist);
        return new ResponseMessage("Thành công", "Bài hát đã được thêm vào PlayList");
    }

}
