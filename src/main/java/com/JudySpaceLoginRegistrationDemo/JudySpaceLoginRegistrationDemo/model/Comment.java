package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Table(name = "comment")
@Entity
@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("postedComments")
    private Users poster;
    @Column(name = "content")
    private String content;
    @Column(name = "create_date", insertable = false, updatable = false)
    private Instant createDate;
    @Column(name = "is_root")
    private boolean isRoot;
    @ManyToOne
    @JoinColumn(name = "blog_id")
    @JsonIgnoreProperties("comment")
    private Blog blogRepliedTo;
    @ManyToOne
    @JoinColumn(name = "comment_rep_id")
    @JsonIgnoreProperties("childComments")
    private Comment parentComment;
    @OneToMany(mappedBy = "parentComment")
    @JsonIgnoreProperties("parentComment")
    private Set<Comment> childComments;
    @Column(name = "is_delete", insertable = false)
    private boolean isDeleted;
    @Column(name = "delete_date")
    private Instant deleteDate;
    @Column(name = "updateDate")
    private Instant updateDate;
    @OneToMany(mappedBy = "comment", orphanRemoval = true)
    @JsonIgnoreProperties("comment")
    private Set<CommentUpvote> upvotedUsers;
    public Integer getNumberOfChild(Comment root) {
        return getNumberOfChildRecursive(root, 1);
    }

    private Integer getNumberOfChildRecursive(Comment comment, int sum) {
        if (comment == null) {
            return sum;
        }

        Set<Comment> childComments = comment.getChildComments();
        sum += childComments.size();

        for (Comment c : childComments) {
            sum = getNumberOfChildRecursive(c, sum);
        }

        return sum;
    }

}
