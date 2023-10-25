package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blogtag")
public class BlogTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "blogTag")
    @JsonIgnoreProperties("blogTag")
    private Set<Blog> blogs;
    @Column(name = "create_date")
    private Instant createDate;
    @Column(name = "is_delete")
    private boolean isDeleted;
    @Column(name = "delete_date")
    private Instant deleteDate;
    @Column(name = "updated_date")
    private Instant updatedDate;
}
