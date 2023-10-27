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
    @Query(value = "Select b.* from blog b " +
            "left join comment c " +
            "on (:sortType is null or b.blog_id  = c.blog_id) " +
            "and (:sortType is Null " +
            "or (:sortType = 'popularity24h' and c.create_date >= current_timestamp - interval '24 hours') " +
            "or (:sortType = 'popularityWeek' and DATE_TRUNC('WEEK', c.create_date) = DATE_TRUNC('WEEK', CURRENT_DATE))) " +
            "and c.is_delete = false " +
            "where b.is_deleted = false " +
            "and (:searchName is null or LOWER(b.title) like CONCAT('%', LOWER(:searchName), '%')) " +
            "and (:tagId is null or b.tag_id = :tagId) " +
            "group by c.blog_id, b.blog_id , b.title, b.blog_thumbnail , b.create_date, b.is_deleted , b.deleted_date, b.update_date, b.category_id, b.tag_id\n" +
            "order by count(c.comment_id) desc", nativeQuery = true)
    public Page<Blog> getBlogsByPage(@Param("searchName") String searchName, @Param("tagId") Integer tagId, @Param("sortType") String sortType,Pageable pageable);

}
