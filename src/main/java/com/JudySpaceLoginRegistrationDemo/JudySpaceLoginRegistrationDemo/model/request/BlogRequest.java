package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogCategory;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogTag;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvote;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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
public class BlogRequest {
    @NotNull(groups = {UpdateRequest.class})
    @Null(groups = {AddRequest.class})
    private Integer blogId;
    private String title;
    private String blogThumbnail;
    private Instant createDate;
    private boolean isDeleted;
    private Instant updateDate;
    private Instant deleteDate;
    private BlogCategory blogCategory;
    private BlogTag blogTag;

    public interface AddRequest{

    }
    public interface UpdateRequest{

    }
}
