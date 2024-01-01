package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "paragraph_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageParagraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;
    @Column(name = "image_link")
    private String imageLink;
    @ManyToOne
    @JoinColumn(name = "paragraph_id")
    @JsonIgnoreProperties("imageParagraphs")
    private Paragraph paragraph;
    @Column(name = "created_date", insertable = false, updatable = false)
    private Instant createDate;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "deleted_date", insertable = false)
    private Instant deletedDate;
    @Column(name = "update_date", insertable = false)
    private Instant updateDate;
    @OneToMany(mappedBy = "parentImage", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("parentImage")
    @OrderBy("id")
    private List<ChildImages> childImages;

}
