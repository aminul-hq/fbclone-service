package com.clone.fbclone.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "files")
@AllArgsConstructor
@RequiredArgsConstructor
public class FileEntity extends BaseIdentity<FileEntity> implements Serializable {
    @NonNull
    private String name;
    @NonNull
    private String type;
    @NonNull
    private String url;
    @Lob
    @Column(name = "file_data")
    private byte[] data;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;

    public FileEntity(String fileName, String contentType, byte[] bytes) {
        this.name = fileName;
        this.type = contentType;
        this.data = bytes;
    }

    public FileEntity() {
    }
}
