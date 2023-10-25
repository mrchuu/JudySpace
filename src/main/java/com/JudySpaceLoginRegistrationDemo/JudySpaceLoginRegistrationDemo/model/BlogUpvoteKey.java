package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogUpvoteKey implements Serializable {
    @Column(name = "blog_id")
    private Integer blogId;
    @Column(name = "user_id")
    private Integer userId;
}
