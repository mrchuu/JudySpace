package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    private String userName;
    private String password;
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
