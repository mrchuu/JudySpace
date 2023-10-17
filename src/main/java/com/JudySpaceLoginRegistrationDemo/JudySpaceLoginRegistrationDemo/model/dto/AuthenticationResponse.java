package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;
}
