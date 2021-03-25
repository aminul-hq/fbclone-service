package com.clone.fbclone.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Table(name = "files")
@AllArgsConstructor
@RequiredArgsConstructor
public class FileEntity extends BaseIdentity<FileEntity>{
    @NonNull
    private String name;
    @NonNull
    private String type;
    @Lob
    private byte[] data;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    UserEntity user;

    public FileEntity(String fileName, String contentType, byte[] bytes) {
        this.name = fileName;
        this.type = contentType;
        this.data = bytes;
    }

    public FileEntity() {

    }
}
