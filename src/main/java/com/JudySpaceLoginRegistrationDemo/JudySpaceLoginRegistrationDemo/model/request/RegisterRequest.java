package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotBlank(message = "Tên người dùng không được để trống")
    private String userName;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$", message = "Mật khẩu cần phải có 6 đến 20 kí tự gồm các chữ cái và chữ số")
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
    @NotBlank(message = "Email không được để trống")
    private String email;
    private String avatarLink;
    private boolean isEnabled;

    public RegisterRequest(String userName, String password, String email, String avatarLink) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.avatarLink = avatarLink;
        this.isEnabled = false;
    }
}
