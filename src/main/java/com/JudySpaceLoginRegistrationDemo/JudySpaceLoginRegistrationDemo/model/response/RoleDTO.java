package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
public class RoleDTO {
    private int roleId;
    private String roleName;
}
