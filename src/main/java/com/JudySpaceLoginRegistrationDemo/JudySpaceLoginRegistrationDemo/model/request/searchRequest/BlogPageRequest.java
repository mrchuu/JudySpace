package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.searchRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class BlogPageRequest extends SearchRequest{
    private String searchName;
    private String sortType;
    private Integer tagId;
    private Integer categoryId;
    private List<Integer> movieCategories;
    public BlogPageRequest(int pageIndex, int pageSize, String searchName, String sortType, Integer tagId, Integer categoryId, List<Integer> movieCategories) {
        super(pageIndex, pageSize);
        this.searchName = searchName;
        this.sortType = sortType;
        this.tagId = tagId;
        this.categoryId = categoryId;
        this.movieCategories = movieCategories;
    }

    public BlogPageRequest(int pageIndex, int pageSize) {
        super(pageIndex, pageSize);
    }
    public BlogPageRequest() {

    }
}
