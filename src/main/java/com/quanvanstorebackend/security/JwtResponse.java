package com.quanvanstorebackend.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String userCode;
    private String username;
    private String role;
    private String jwtToken;
}
