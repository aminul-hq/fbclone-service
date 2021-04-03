package com.clone.fbclone.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "user_details")
public class UserEntity extends BaseIdentity<UserEntity> implements UserDetails, Serializable {
    @NonNull
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String firstName;
    @NonNull
    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String lastName;

    @Setter(AccessLevel.PRIVATE)
    String username;

    @NonNull
    @Column(nullable = false, unique = true, columnDefinition = "varchar(255)")
    private String email;
    @NonNull
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles authorities;
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "boolean default true")
    private Boolean isExpired;
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "boolean default true")
    private Boolean isLocked;
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "boolean default true")
    private Boolean isCredExpired;
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "boolean default false")
    private Boolean isEnabled;
    @JsonIgnore
    @OneToMany(targetEntity = FileEntity.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileEntity> images;



    public UserEntity() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(this.authorities);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @PrePersist
    void beforeSave() {
        this.isEnabled = true;
        this.isCredExpired = true;
        this.isExpired = true;
        this.isLocked = true;
        this.username = firstName + " "+ lastName;
    }
}
