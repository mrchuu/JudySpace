package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "blog_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "blogCategory")
    @JsonIgnoreProperties("blogCategory")
    private Set<Blog> blogList;
}
