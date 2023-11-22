package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.MovieCategory;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.MovieCategoryRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.MovieCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieCategoryMapper extends EntityMapper<MovieCategoryDTO, MovieCategory, MovieCategoryRequest>{
}
