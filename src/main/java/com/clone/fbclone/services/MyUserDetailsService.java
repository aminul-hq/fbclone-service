package com.clone.fbclone.services;

import com.clone.fbclone.entities.Roles;
import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return repo.findByUserName(userName);
    }

    public UserEntity createUser(UserEntity entity){
        return repo.save(entity);
    }

//    new UserEntity("darkness", "14110018", Roles.ADMIN, true, true, true, true);

}
