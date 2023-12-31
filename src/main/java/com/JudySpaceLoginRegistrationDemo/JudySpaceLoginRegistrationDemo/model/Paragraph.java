package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "paragraph")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Paragraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paragraphId;
    @Column(name = "paragraph_content")
    private String paragraphContent;
    @ManyToOne
    @JoinColumn(name = "blog_id", updatable = false)
    @JsonIgnoreProperties("paragraphs")
    private Blog blog;
    @Column(name = "create_date", insertable = false, updatable = false)
    private Instant createDate;
    @Column(name = "is_delete")
    private boolean isDeleted;
    @Column(name = "delete_date", insertable = false)
    private Instant deleteDate;
    @UpdateTimestamp
    @Column(name = "update_date", insertable = false)
    private Instant updateDate;
    @OneToMany(mappedBy = "paragraph", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("paragraph")
    @OrderBy("imageId")
    private Set<ImageParagraph> imageParagraphs;
}
