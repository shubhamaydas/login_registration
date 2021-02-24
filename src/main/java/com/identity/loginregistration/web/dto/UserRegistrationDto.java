package com.identity.loginregistration.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
