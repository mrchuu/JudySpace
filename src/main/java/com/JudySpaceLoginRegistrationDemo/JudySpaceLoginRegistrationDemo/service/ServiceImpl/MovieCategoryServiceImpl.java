package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ServiceImpl;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper.MovieCategoryMapper;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MovieCategoryDTO;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository.MovieCategoriesRepository;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.MovieCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class MovieCategoryServiceImpl implements MovieCategoryService {
    private MovieCategoriesRepository movieCategoriesRepository;
    private MovieCategoryMapper movieCategoryMapper;

    @Override
    public List<MovieCategoryDTO> getAll() {
        return movieCategoriesRepository.findAll().stream().map(movieCategoryMapper::toDto).collect(Collectors.toList());
    }
}
