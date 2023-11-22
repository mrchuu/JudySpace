package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.MovieCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
    private Integer blogId;
    private String title;
    public String caption;
    private String blogThumbnail;
    private Instant createDate;
    private BlogCategoryDTO blogCategory;
    private Integer upvoteUserSetSize;
    private Integer commentSetSize;
    private BlogTagDTO blogTag;
    private boolean upvotedByCurrentUser;
    private String youtubeLink;
    private Set<MovieCategoryDTO> movieCategories;
}
