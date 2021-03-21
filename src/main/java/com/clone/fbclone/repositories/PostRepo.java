package com.clone.fbclone.repositories;

import com.clone.fbclone.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aminul Hoque
 * @since 2021-03-21
 */
public interface PostRepo extends JpaRepository<PostEntity, String> {
}
