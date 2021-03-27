package com.clone.fbclone.services;


import com.clone.fbclone.repositories.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.clone.fbclone.entities.FileEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileService {
    @Autowired
    private FileRepo repo;

    public FileEntity store(MultipartFile file) throws IOException{
        String fileName = file.getOriginalFilename();
        FileEntity fileEntity = new FileEntity(fileName, file.getContentType(), file.getBytes());
        return repo.save(fileEntity);
    }

    public FileEntity saveUserImage(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getName());
        FileEntity fileEntity = new FileEntity(fileName, file.getContentType(), file.getBytes());
        return fileEntity;
    }
    public FileEntity getFile(String fileId) {
        return repo.findById(fileId).get();
    }

    public Stream<FileEntity> getAllFiles() {
        return repo.findAll().stream();
    }

}
