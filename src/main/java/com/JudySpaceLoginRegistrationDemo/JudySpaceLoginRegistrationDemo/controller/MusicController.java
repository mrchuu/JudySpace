package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.MusicPlaylistRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.MusicRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MusicDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MusicPlayListDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.MusicPlayListService;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.MusicService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/music/")
public class MusicController {
    private MusicPlayListService musicPlayListService;
    private MusicService musicService;

    @GetMapping("playlist/getAll")
    public ResponseEntity<List<MusicPlayListDTO>> getAllPlayList() {
        return ResponseEntity.ok(musicPlayListService.getAllPlayList());
    }
    @GetMapping("getAll")
    public ResponseEntity<List<MusicDTO>> getAllSongs() {
        return ResponseEntity.ok(musicService.getAllSongs());
    }
    @GetMapping("playList/getSongs/{playListId}")
    public ResponseEntity<List<MusicDTO>> getSongsOfPlayList(@PathVariable(name = "playListId") Integer playListId) {
        return ResponseEntity.ok(musicPlayListService.getAllSongOfPlayList(playListId));
    }

    @PostMapping("add")
    public ResponseEntity<ResponseMessage> addSong(@RequestBody MusicRequest musicRequest) {
        return ResponseEntity.ok(musicService.addSong(musicRequest));
    }

    @PostMapping("playList/add")
    public ResponseEntity<ResponseMessage> addPlayList(@RequestBody MusicPlaylistRequest playListRequest) {
        return ResponseEntity.ok(musicPlayListService.addPlayList(playListRequest));
    }

    @PostMapping("addOrRemoveFromPlayList/{songId}/{playListId}")
    public ResponseEntity<ResponseMessage> addSongToPlaylist(
            @PathVariable(name = "songId") Integer songId,
            @PathVariable(name = "playListId") Integer playListId) {
        return ResponseEntity.ok(musicPlayListService.addOrRemoveFromPlayList(songId, playListId));
    }
    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> handleCommonException(EntityExistsException e) {
        ResponseMessage rm = new ResponseMessage("Lỗi", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rm);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessage> handleCommonException(EntityNotFoundException e) {
        ResponseMessage rm = new ResponseMessage("Không tìm thấy", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(rm);
    }
}
