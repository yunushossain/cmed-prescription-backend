package com.cmed.prescription.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
