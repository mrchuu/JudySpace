package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotNull(message = "Tên người dùng không được để trống")
    @Pattern(regexp = "^[A-Za-z0-9 ]{6,20}$\n", message = "Tên người dùng cần có 6 đến 20 ký tự, bao gồm chữ cái và chữ số")
    private String userName;
    @Pattern(regexp = "^(?=.*[A-Za-z0-9]{6,20}$)\n", message = "Mật khẩu cần phải có 6 đến 20 kí tự gồm các chữ cái và chữ số")
    @NotNull(message = "Mật khẩu không được để trống")
    private String password;
    @NotNull(message = "Email không được để trống")
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
