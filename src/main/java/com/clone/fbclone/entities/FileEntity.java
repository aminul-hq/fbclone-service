package com.clone.fbclone.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Entity
@Data
@Table(name = "files")
@AllArgsConstructor
@Transactional
@RequiredArgsConstructor
public class FileEntity extends BaseIdentity<FileEntity> implements Serializable {
    @NonNull
    private String name;
    @NonNull
    private String type;
    @NonNull
    private String url;
    @Lob
    private byte[] data;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    UserEntity user;

    public FileEntity(String fileName, String contentType, byte[] bytes) {
        this.name = fileName;
        this.type = contentType;
        this.data = bytes;
    }

    public FileEntity() {
    }
}
