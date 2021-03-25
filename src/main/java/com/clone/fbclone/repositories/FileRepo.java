package com.clone.fbclone.repositories;

import com.clone.fbclone.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<FileEntity, String> {
}
