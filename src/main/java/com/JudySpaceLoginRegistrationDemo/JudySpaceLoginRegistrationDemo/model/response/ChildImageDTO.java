package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.ImageParagraph;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildImageDTO {
    private Integer id;
    private String imageLink;
    private Integer topIndex;
    private Integer leftIndex;
    private Integer width;
}
