package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
@Getter
@AllArgsConstructor
public class BlogCategoryRequest {
    @NotNull(groups = {UpdateRequest.class})
    @Null(groups = {AddRequest.class})
    private Integer id;
    private String name;

    public interface AddRequest{

    }
    public interface UpdateRequest{

    }
}
