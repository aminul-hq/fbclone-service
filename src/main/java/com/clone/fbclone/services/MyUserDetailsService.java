package com.clone.fbclone.services;


import com.clone.fbclone.entities.FileEntity;
import com.clone.fbclone.entities.Roles;
import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.repositories.FileRepo;
import com.clone.fbclone.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    FileRepo fileRepo;
    @Autowired
    private UserRepo repo;
    @Autowired
    private FileService service;
    @Override
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email);
    }

    public UserEntity createUser(UserEntity entity) {
        return repo.save(entity);
    }

    public UserEntity createUser(String fname, String lname, String email, String pass, String auth, MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        UserEntity entity = new UserEntity()
                .setFirstName(fname)
                .setLastName(lname)
                .setEmail(email)
                .setPassword(pass)
                .setAuthorities(Roles.USER);
        UserEntity user = repo.save(entity);
        fileEntity.setUser(user);
        fileRepo.save(fileEntity);
        return user;
    }

    public Optional<UserEntity> getUserDetails(String id){
        return repo.findById(id);
    }


}
