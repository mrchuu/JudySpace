package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Comment;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
public class CommentRequest {
    @NotNull(groups = {UpdateRequest.class})
    private Integer commentId;
    private Users poster;
    private String content;
    private boolean isRoot;
    private Blog blogRepliedTo;
    private Comment parentComment;
    private boolean isDeleted;
    private Instant deleteDate;
    private Instant updateDate;
    public interface AddRequest{

    }
    public interface UpdateRequest{

    }
}
