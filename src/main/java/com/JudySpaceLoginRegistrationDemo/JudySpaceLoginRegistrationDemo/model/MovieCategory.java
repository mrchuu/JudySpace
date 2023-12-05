package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Set;

@Table(name = "movie_category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieCategoryId;
    @Column(name = "catogory_name")
    private String categoryName;
    @Column(name = "create_date")
    @CreatedDate
    private Instant createDate;
    @Column(name = "updated_date")
    @UpdateTimestamp
    private Instant updateDate;
    @Column(name = "delete_date")
    private Instant deletedDate;
    @Column(name = "is_deleted", insertable = false)
    private boolean isDeleted;
    @ManyToMany(mappedBy = "movieCategories", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("movieCategories")
    private Set<Blog> moviesBelongedTo;
}
