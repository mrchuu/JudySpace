package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
public class BlogDTO {
    private Integer blogId;
    private String title;
    private String blogThumbnail;
    private Instant createDate;
    private BlogCategoryDTO blogCategory;
    private Integer upvoteUserSetSize;
    private Integer commentSetSize;
    private BlogTagDTO blogTag;
}
