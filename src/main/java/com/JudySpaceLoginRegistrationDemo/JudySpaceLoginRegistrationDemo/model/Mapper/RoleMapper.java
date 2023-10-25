package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Mapper;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.Role;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.RoleRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.response.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role, RoleRequest>{
}
