package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangePasswordRequest {
    @NotNull(groups = {changePassword.class, resetPassword.class}, message = "Invalid Email")
    private String email;
    @NotNull(groups = {changePassword.class}, message = "Old Password must be specified when changing password")
    @Null(groups = {resetPassword.class}, message = "Unnecessary data")
    private String oldPassword;
    @NotNull(groups = {changePassword.class}, message = "Old Password must be specified when changing password")
    @Null(groups = {resetPassword.class}, message = "Unnecessary data")
    private String newPassword;

    public interface changePassword{

    }
    public interface resetPassword{

    }
}
