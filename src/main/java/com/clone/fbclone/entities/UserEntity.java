package com.clone.fbclone.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
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
public class UserEntity extends BaseIdentity<UserEntity> implements UserDetails {

    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Roles authorities;
    @Column(columnDefinition = "boolean default true")
    private Boolean isExpired;
    @Column(columnDefinition = "boolean default false")
    private Boolean isLocked;
    @Column(columnDefinition = "boolean default false")
    private Boolean isCredExpired;
    @Column(columnDefinition = "boolean default false")
    private Boolean isEnabled;

    public UserEntity(String userName, String password, Roles roles, boolean isEnabled, boolean isLocked, boolean isExpired, boolean isCredExpired) {
        this.userName = userName;
        this.password = password;
        this.authorities = roles;
        this.isEnabled = isEnabled;
        this.isLocked = isLocked;
        this.isExpired = isExpired;
        this.isCredExpired = isCredExpired;

    }

    public UserEntity() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(this.authorities);
    }

    @Override
    public String getUsername() {
        return this.userName;
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
