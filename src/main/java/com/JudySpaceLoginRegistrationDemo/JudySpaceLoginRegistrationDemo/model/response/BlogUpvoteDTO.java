package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvoteKey;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
public class BlogUpvoteDTO {
    private Instant createDate;
    private UserDTO user;
}
