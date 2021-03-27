package com.clone.fbclone.repositories;

import com.clone.fbclone.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepo extends JpaRepository<FileEntity, String> {
}
