package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("Select c from Comment c where c.blogRepliedTo.blogId = :blogId " +
            "and c.isRoot = true")
    public List<Comment> getRootComments(@Param("blogId") Integer blogId);
    @Query("Select c from Comment c where c.parentComment.commentId = :repliedTo ")
    public List<Comment> getChildComment(@Param("repliedTo") Integer comment);
}
