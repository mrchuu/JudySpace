package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.BlogUpvote;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int userId;
    private String userName;
    private String email;
    private RoleDTO role;
    private String avatarLink;
}
