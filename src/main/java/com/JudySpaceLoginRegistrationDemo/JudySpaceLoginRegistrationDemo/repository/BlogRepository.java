package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    @Query(value = "Select b.* from Blog b where b.is_deleted = false " +
            "AND (b.blogCategory.id = 1) " +
            "AND (:searchName IS NULL OR LOWER(b.title) LIKE CONCAT ('%', LOWER(:searchName),'%')) " +
            "AND (:tagId = 0 OR b.blogTag.id = :tagId) ", nativeQuery = true)
    public Page<Blog> getBlogsByPage(@Param("searchName") String searchName, @Param("tagId") Integer tagId, Pageable pageable);

}
