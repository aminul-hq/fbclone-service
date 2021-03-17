package com.clone.fbclone.controller;

import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */
@RestController
public class UserController {
    @Autowired
    private MyUserDetailsService service;
    @GetMapping
    public ResponseEntity userLogin(){
        return ResponseEntity.ok().body("user login is working");
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserEntity entity){
        return ResponseEntity.ok().body(service.createUser(entity));
    }
}
