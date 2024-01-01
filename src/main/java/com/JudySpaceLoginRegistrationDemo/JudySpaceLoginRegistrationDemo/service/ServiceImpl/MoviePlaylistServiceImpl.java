package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.MoviePlaylistMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.MoviePlayList;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MoviePlayListDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.BlogRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.MoviePlaylistRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.MoviePlaylistService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MoviePlaylistServiceImpl implements MoviePlaylistService {
    private MoviePlaylistRepository moviePlaylistRepository;
    private BlogRepository blogRepository;
    private MoviePlaylistMapper moviePlaylistMapper;

    @Override
    public List<MoviePlayListDTO> getAllPlayList() {
        return moviePlaylistRepository.findAll().stream().map(moviePlaylistMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addMovieToPlayList(Integer playListId, Integer blogId) {
        MoviePlayList playList = moviePlaylistRepository.findById(playListId).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy playlist"));
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phim"));
        playList.getMoviesOfPlaylist().add(blog);
        blog.getPlayListOfMovie().add(playList);
        moviePlaylistRepository.save(playList);
        blogRepository.save(blog);
    }
}
