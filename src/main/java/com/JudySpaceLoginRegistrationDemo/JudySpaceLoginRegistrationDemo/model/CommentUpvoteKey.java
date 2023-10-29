package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentUpvoteKey implements Serializable {
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "comment_id")
    private Integer commentId;
}
