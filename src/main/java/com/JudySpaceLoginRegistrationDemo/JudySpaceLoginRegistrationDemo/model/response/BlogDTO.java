package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
//    private Set<BlogUpvoteDTO> upvotedUsers;
//    private Set<CommentDTO> comments;
    private Integer upvoteUserSetSize;
    private Integer commentSetSize;

}
