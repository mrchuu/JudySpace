package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "blog_upvote")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogUpvote {
    @EmbeddedId
    private BlogUpvoteKey blogUpvoteKey;
    @Column(name = "create_date")
    private Instant createDate;
    @ManyToOne
    @MapsId("blogId")
    @JoinColumn(name = "blog_id")
    @JsonIgnoreProperties("upvotedUsers")
    private Blog blog;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("upvotedBlogs")
    private Users user;
}
