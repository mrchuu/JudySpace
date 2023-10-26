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

    public BlogPageRequest(int pageIndex, int pageSize, String searchName, String sortType, Integer tagId) {
        super(pageIndex, pageSize);
        this.searchName = searchName;
        this.sortType = sortType;
        this.tagId = tagId;
    }

    public BlogPageRequest(int pageIndex, int pageSize) {
        super(pageIndex, pageSize);
    }
    public BlogPageRequest() {

    }
}
