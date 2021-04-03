package com.clone.fbclone.controller;

import com.clone.fbclone.entities.FileEntity;
import com.clone.fbclone.entities.Roles;
import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.services.FileService;
import com.clone.fbclone.services.MyUserDetailsService;
import com.clone.fbclone.services.model.AuthenticationRequest;
import com.clone.fbclone.services.model.AuthenticationResponse;
import com.clone.fbclone.services.model.UserDTO;
import com.clone.fbclone.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private FileService fileService;
    @Autowired
    private MyUserDetailsService service;
    @Autowired
    private JwtUtil util;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/validate")
    public ResponseEntity userLogin() {
        return ResponseEntity.ok().body("user login is working");
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserEntity entity)
            throws IOException {
        return ResponseEntity.ok().body(service.createUser(entity));
    }

    @PostMapping("/create/attached")
    public ResponseEntity createUser(@RequestParam("file" ) MultipartFile file, @RequestParam("fname") String fname,
                                     @RequestParam("lname") String lname, @RequestParam("email") String email,
                                     @RequestParam("pass") String pass, @RequestParam("auth") String auth)
            throws IOException {
        return ResponseEntity.ok().body(service.createUser(fname, lname, email, pass, auth, file));
    }

    @PostMapping("/authenticate")
    public ResponseEntity createAuthToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken
                            (request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserEntity user = service.loadUserByUsername(request.getEmail());
        final String jwt = util.generateToken(user);
        return ResponseEntity.ok(new AuthenticationResponse(user.getId(), jwt));
    }

    /**
     * TODO create refresh token system in here
     */

    @PostMapping("/refresh")
    public String getRefreshToken() {
        return "refresh token";
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserDetails(@PathVariable("id") String id) {
        return service.getUserDetails(id);
    }
}
