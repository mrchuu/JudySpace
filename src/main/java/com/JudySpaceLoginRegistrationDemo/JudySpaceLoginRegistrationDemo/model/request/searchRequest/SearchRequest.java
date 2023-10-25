package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.searchRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SearchRequest {
    private int pageIndex;
    private int pageSize;
}
