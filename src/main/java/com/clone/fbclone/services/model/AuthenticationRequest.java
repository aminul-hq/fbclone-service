package com.clone.fbclone.services.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @NoArgsConstructor @AllArgsConstructor
public class AuthenticationRequest  {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
