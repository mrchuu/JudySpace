package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;

@Table(name = "blog")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;
    @Column(name = "title")
    private String title;
    @Column(name = "blog_thumbnail")
    private String blogThumbnail;
    @Column(name = "create_date", insertable = false, updatable = false)
    private Instant createDate;
    @Column(name = "is_deleted", insertable = false)
    private boolean isDeleted;
    @UpdateTimestamp
    @Column(name = "update_date", insertable = false)
    private Instant updateDate;
    @Column(name = "deleted_date", insertable = false)
    private Instant deleteDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("blogList")
    private BlogCategory blogCategory;
    @OneToMany(mappedBy = "blog")
    @JsonIgnoreProperties("blog")
    private Set<BlogUpvote> upvotedUsers;
    @OneToMany(mappedBy = "blogRepliedTo", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("blogRepliedTo")
    private Set<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    @JsonIgnoreProperties("blogs")
    private BlogTag blogTag;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("blog")
    private Set<Paragraph> paragraphs;
    @Column(name = "caption")
    private String caption;
    @Column(name = "youtube_link")
    private String youtubeLink;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "blog_movie_catgory",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_category_id")
    )
    @JsonIgnoreProperties("moviesBelongedTo")
    private Set<MovieCategory> movieCategories;
    @Column(name = "blog_hash_tags")
    private String blogHashTags;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_playlist_blog",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    @JsonIgnore
    private Set<MoviePlayList> playListOfMovie;
}
