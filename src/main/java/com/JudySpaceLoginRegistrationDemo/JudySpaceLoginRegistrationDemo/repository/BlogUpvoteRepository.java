package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvote;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvoteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogUpvoteRepository extends JpaRepository<BlogUpvote, Integer> {
    @Query("Select bu from BlogUpvote bu where " +
            "bu.blogUpvoteKey.blogId = :blogId and " +
            "bu.blogUpvoteKey.userId = :userId")
    public BlogUpvote findById(@Param("blogId")Integer blogId, @Param("userId")Integer userId);

    @Query("Select bu from BlogUpvote bu where bu.blogUpvoteKey.blogId = :blogId")
    public List<BlogUpvote> getBlogUpvoteByBlogId(@Param("blogId") Integer blogId);
}
