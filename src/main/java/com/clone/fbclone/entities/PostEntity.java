package com.clone.fbclone.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Aminul Hoque
 * @since 2021-03-21
 */
@Entity
@Table(name = "posts")
@Data
public class PostEntity extends BaseIdentity<PostEntity>{

    private String title;
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "timestamp not null default now()")
    private LocalDateTime time;
    private String avatar;
    @Column(columnDefinition = "text not null")
    private String message;

    @PrePersist
    protected void beforeSave(){
        this.time = LocalDateTime.now();
    }
}
