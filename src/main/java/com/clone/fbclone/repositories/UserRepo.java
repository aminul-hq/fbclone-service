package com.clone.fbclone.repositories;

import com.clone.fbclone.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */
public interface UserRepo extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String userName);
}
