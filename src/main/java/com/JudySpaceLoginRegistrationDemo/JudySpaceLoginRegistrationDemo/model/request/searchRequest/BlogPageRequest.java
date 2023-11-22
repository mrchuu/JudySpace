package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.searchRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

public class BlogPageRequest extends SearchRequest{
    private String searchName;
    private String sortType;
    private Integer tagId;
    private Integer categoryId;
    public BlogPageRequest(int pageIndex, int pageSize, String searchName, String sortType, Integer tagId, Integer categoryId) {
        super(pageIndex, pageSize);
        this.searchName = searchName;
        this.sortType = sortType;
        this.tagId = tagId;
        this.categoryId = categoryId;
    }

    public BlogPageRequest(int pageIndex, int pageSize) {
        super(pageIndex, pageSize);
    }
    public BlogPageRequest() {

    }
}
