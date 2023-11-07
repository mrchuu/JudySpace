package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Paragraph;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageParagraphDTO {
    private Integer imageId;
    private String imageLink;
}
