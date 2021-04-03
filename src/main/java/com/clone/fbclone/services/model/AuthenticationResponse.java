package com.clone.fbclone.services.model;


import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String id;
    private final String jwt;
}
