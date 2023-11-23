package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.repository;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Paragraph;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    @Query(value = "select cj.* from " +
            "(select b.*, count(c.comment_id) as commentCount from blog b " +
            "left join comment c on b.blog_id  = c.blog_id " +
            "And (:sortType is null " +
            "or (:sortType = 'popularityAllTime') " +
            "or (:sortType = 'popularity24h' and c.create_date >= current_timestamp - interval '24 hours') " +
            "or (:sortType = 'popularityWeek' and DATE_TRUNC('WEEK', c.create_date) = DATE_TRUNC('WEEK', CURRENT_DATE))) " +
            "and c.is_delete = false " +
            "where b.is_deleted = false " +
            "group by c.blog_id,b.blog_id , b.title, b.blog_thumbnail , b.create_date, b.is_deleted , b.deleted_date, b.update_date, b.category_id, b.tag_id) as cj, " +
            "(select b.*, count(bu.user_id) as upvoteCount from blog b " +
            "left join blog_upvote bu on b.blog_id = bu.blog_id  " +
            "And (:sortType is null " +
            "or (:sortType = 'popularityAllTime') " +
            "or (:sortType = 'popularity24h' and bu.create_date >= current_timestamp - interval '24 hours') " +
            "or (:sortType = 'popularityWeek' and DATE_TRUNC('WEEK', bu.create_date) = DATE_TRUNC('WEEK', CURRENT_DATE))) " +
            "where b.is_deleted = false " +
            "group by bu.blog_id,b.blog_id , b.title, b.blog_thumbnail , b.create_date, b.is_deleted , b.deleted_date, b.update_date, b.category_id, b.tag_id) " +
            "as buj, " +
            "blog_movie_catgory bmc " +
            "where cj.blog_id = buj.blog_id " +
            "and (:searchName is Null or Lower(cj.title) like CONCAT('%', LOWER(:searchName), '%')) " +
            "and (:tagId is Null or cj.tag_id = :tagId) " +
            "and (:category_id is Null or cj.category_id = :category_id) " +
            "and (:cateSize = 0 or cj.blog_id = bmc.blog_id ) " +
            "and (:cateSize = 0 or bmc.movie_category_id IN :movieCategories)" +
            "group by cj.youtube_link, cj.caption, cj.blog_id, cj.title, cj.blog_thumbnail, cj.create_date, cj.is_deleted, cj.deleted_date, cj.update_date, cj.category_id, cj.tag_id, cj.commentCount, buj.blog_id, buj.upvoteCount " +
            "having (:cateSize = 0 or COUNT(DISTINCT bmc.movie_category_id) = :cateSize )" +
            "ORDER BY " +
            "CASE " +
            "WHEN :sortType = 'popularity24h' THEN sum(buj.upvoteCount + cj.commentCount) " +
            "WHEN :sortType = 'popularityAllTime' THEN sum(buj.upvoteCount + cj.commentCount) " +
            "WHEN :sortType = 'popularityWeek' THEN sum(buj.upvoteCount + cj.commentCount) " +
            "end desc, " +
            "Case " +
            "WHEN :sortType = 'latest' THEN cj.create_date end desc, " +
            "Case " +
            "WHEN :sortType = 'oldest' then cj.create_date end asc",
            countQuery = "select count(b.blog_id) from blog b " +
                    "where b.is_deleted = false "
            , nativeQuery = true)
    public Page<Blog> getBlogsByPage(
            @Param("searchName") String searchName,
            @Param("tagId") Integer tagId,
            @Param("sortType") String sortType,
            @Param("category_id") Integer categoryId,
            @Param("movieCategories") List<Integer> movieCategories,
            @Param("cateSize") Integer cateSize,
            Pageable pageable);

    @Query("Select p from Paragraph p where p.blog.blogId = :blogId")
    public List<Paragraph> getBlogDetails(@Param("blogId") Integer blogId);
}
