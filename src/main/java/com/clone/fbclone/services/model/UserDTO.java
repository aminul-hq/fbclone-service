package com.clone.fbclone.services.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class UserDTO {

    private String id;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private List<Images> images;


    public UserDTO() {

    }

    public UserDTO(String id, String username, String email, Collection<? extends GrantedAuthority> authorities, List<Images> asList) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.authorities = authorities;
        this.images =  asList;
    }

    @Data
    public static class Images {
        private String url;

        public Images(String url) {
            this.url = url;
        }
    }


}
