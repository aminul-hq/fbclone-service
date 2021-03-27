package com.clone.fbclone.controller;

import com.clone.fbclone.entities.FileEntity;
import com.clone.fbclone.repositories.FileRepo;
import com.clone.fbclone.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.persistence.PrePersist;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService service;
    @Autowired
    private FileRepo repo;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            FileEntity entity = service.store(file);
            entity.setUrl(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/file")
                    .path("/download/")
                    .path(entity.getId())
                    .toUriString());
            repo.save(entity);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity downloadFile(@PathVariable String fileId) {
        // Load file from database
        FileEntity entity = service.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(entity.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + entity.getName() + "\"")
                .body(new ByteArrayResource(entity.getData()));
    }

//    @GetMapping("/files")
//    public ResponseEntity getListFiles() {
//
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(.getId())
//                    .toUriString();
//
//            return new ResponseFile(
//                    dbFile.getName(),
//                    fileDownloadUri,
//                    dbFile.getType(),
//                    dbFile.getData().length);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }

//    @GetMapping("/files/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
//        FileDB fileDB = storageService.getFile(id);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//                .body(fileDB.getData());
//    }
}
