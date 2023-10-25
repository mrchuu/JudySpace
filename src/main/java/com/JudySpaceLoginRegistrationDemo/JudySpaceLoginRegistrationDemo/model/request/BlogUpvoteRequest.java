package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Blog;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvoteKey;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
@AllArgsConstructor
@Getter
public class BlogUpvoteRequest {
    @NotNull
    private BlogUpvoteKey blogUpvoteKey;
}
