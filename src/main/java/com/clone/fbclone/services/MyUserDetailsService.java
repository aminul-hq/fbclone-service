package com.clone.fbclone.services;


import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.repositories.FileRepo;
import com.clone.fbclone.repositories.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
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

    public UserEntity createUser(UserEntity entity, MultipartFile file) throws IOException {
        UserEntity userEntity = entity;
        userEntity.setImages(Collections.singletonList(service.saveUserImage(file)));
        return repo.save(entity);
    }


    public UserEntity createUser(UserEntity entity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserEntity userEntity = entity;
//        userEntity.setImage(Arrays.asList(fileRepo.getOne("402881167872ed2c01787380adc60001")));
//        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userEntity));
        return repo.save(entity);
    }

    public Optional<UserEntity> getUserDetails(String id){
        return repo.findById(id);
    }

//    new UserEntity("darkness", "14110018", Roles.ADMIN, true, true, true, true);

}
