package com.atul.jewelry.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

    private String message;

    private String firstName;

    private String lastName;

    private String email;

    private String role;
}