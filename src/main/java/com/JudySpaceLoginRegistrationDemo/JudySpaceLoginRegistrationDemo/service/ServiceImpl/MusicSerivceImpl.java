package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.MusicMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Music;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.MusicRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MusicDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.MusicRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.MusicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MusicSerivceImpl implements MusicService {
    private MusicMapper musicMapper;
    private MusicRepository musicRepository;

    @Override
    public ResponseMessage addSong(MusicRequest musicRequest) {
        Music newSong = musicMapper.toE(musicRequest);
        musicRepository.save(newSong);
        return new ResponseMessage("Thành công", "Bài hát đã được thêm vào kho nhạc");
    }

    @Override
    public List<MusicDTO> getAllSongs() {
        return musicRepository.findAll().stream().map(musicMapper::toDto).collect(Collectors.toList());
    }
}
