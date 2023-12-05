package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "child_images")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChildImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_images_id")
    private Integer id;
    @ManyToOne
    @JsonIgnoreProperties("childImages")
    @JoinColumn(name = "image_id")
    private ImageParagraph parentImage;
    @Column(name = "image_link")
    private String imageLink;
    @Column(name = "top_index")
    private Integer topIndex;
    @Column(name = "left_index")
    private Integer leftIndex;
    @Column(name = "width")
    private Integer width;
}
