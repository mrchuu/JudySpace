package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;

@Entity
@Table(name = "comment_upvote")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentUpvote {
    @EmbeddedId
    private CommentUpvoteKey key;
    @Column(name = "create_date")
    private Instant createDate;
    @ManyToOne
    @MapsId("commentId")
    @JoinColumn(name = "comment_id")
    @JsonIgnoreProperties("upvotedUsers")
    private Comment comment;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("upvotedComments")
    private Users user;
}
