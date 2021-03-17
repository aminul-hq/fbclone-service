package com.clone.fbclone.services.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
public class AuthenticationResponse {
    private final String jwt;
}
