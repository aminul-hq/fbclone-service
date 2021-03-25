package com.clone.fbclone.services;

import com.clone.fbclone.entities.FileEntity;
import com.clone.fbclone.entities.Roles;
import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.repositories.UserRepo;
import com.clone.fbclone.services.model.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private FileService service;
    @Override
    public UserEntity loadUserByUsername(String userName) throws UsernameNotFoundException {
        return repo.findByUsername(userName);
    }

    public UserEntity createUser(UserEntity entity, MultipartFile file) throws IOException {
        UserEntity userEntity = entity;
        userEntity.setImage(Collections.singletonList(service.saveUserImage(file)));
        return repo.save(entity);
    }

//    new UserEntity("darkness", "14110018", Roles.ADMIN, true, true, true, true);

}
