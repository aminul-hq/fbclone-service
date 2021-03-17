package com.clone.fbclone.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "user_details")
@RequiredArgsConstructor @AllArgsConstructor
public class UserEntity extends BaseIdentity<UserEntity> implements UserDetails {
    @NonNull
    @JsonProperty(value = "name")
    @Column(nullable = false, unique = true, length = 255)
    private String username;
    @Column(nullable = false, length = 522)
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

    public UserEntity(String username, String password, Roles userRole) {
        this.username = username;
        this.password = password;
        this.authorities = userRole;
        this.isEnabled = false;
        this.isCredExpired = true;
        this.isExpired = true;
        this.isLocked = true;
    }

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

}
