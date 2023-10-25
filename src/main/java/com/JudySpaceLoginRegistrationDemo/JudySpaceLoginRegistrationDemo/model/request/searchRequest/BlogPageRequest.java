package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.searchRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogPageRequest extends SearchRequest{
    private String searchName;
    private int categoryId;
    private String sortType;

    public BlogPageRequest(int pageIndex, int pageSize, String searchName, int categoryId, String sortType) {
        super(pageIndex, pageSize);
        this.searchName = searchName;
        this.categoryId = categoryId;
        this.sortType = sortType;
    }
}
