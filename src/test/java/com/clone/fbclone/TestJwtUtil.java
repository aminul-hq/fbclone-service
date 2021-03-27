package com.clone.fbclone;

import com.clone.fbclone.entities.Roles;
import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Aminul Hoque
 * @since 2021-03-17
 */

@SpringBootTest
public class TestJwtUtil {
    @Autowired
    private JwtUtil service;

    @Test
    void testTokenGeneration() {
        System.out.println(getToken());
    }

    @Test
    void testValidation() {
        System.out.println(service.validateToken(getToken(), createUser()));
    }

    @Test
    void testExpiration() {
        System.out.println(service.extractExpiration(getToken()));
    }

    @Test
    void testExtraction(){
        System.out.println(service.extractEmail(getToken()));
    }
    private String getToken() {
        return service.generateToken(createUser());
    }

    private UserEntity createUser() {
        return new UserEntity();
    }
}
