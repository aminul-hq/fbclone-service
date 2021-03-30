package com.clone.fbclone;

import com.clone.fbclone.controller.UserController;
import com.clone.fbclone.services.model.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthTest {
    @Autowired
    private UserController controller;
    @Test
    void TestUserAuthController() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("farab@gmail.com","12345678");
        System.out.println(controller.createAuthToken(request));
    }
}
