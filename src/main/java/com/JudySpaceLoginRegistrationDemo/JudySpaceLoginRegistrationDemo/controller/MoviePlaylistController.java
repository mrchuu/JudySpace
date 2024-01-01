package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MoviePlayListDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.ResponseMessage;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.MoviePlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/moviePlaylists/")
public class MoviePlaylistController {
    private MoviePlaylistService moviePlaylistService;

    @GetMapping("getAll")
    public ResponseEntity<List<MoviePlayListDTO>> getAll() {
        return ResponseEntity.ok(moviePlaylistService.getAllPlayList());
    }

    @PutMapping("addMovieToPlayList/{playListId}/{blogId}")
    public ResponseEntity<ResponseMessage> addMovieToPlaylist(@PathVariable(name = "playListId") Integer playListId,
                                                              @PathVariable(name = "blogId") Integer blogId) {
        moviePlaylistService.addMovieToPlayList(playListId, blogId);
        return ResponseEntity.ok(new ResponseMessage("200", "The movie was successfully added to the playlist"));
    }
}
