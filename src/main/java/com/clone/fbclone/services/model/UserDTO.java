package com.clone.fbclone.services.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserDTO {

    private String id;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private List<Images> images;

    public UserDTO(String id, String username, String email,Collection<? extends GrantedAuthority> authorities, List<Images> images) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.images = images;
        this.authorities = authorities;
    }

    public UserDTO() {

    }

    @Data
    public static class Images {
        private ImageType type;
        private String url;

        public Images(ImageType type, String url) {
            this.type = type;
            this.url = url;
        }
    }

    public enum ImageType {
        PROFILE("view"),
        MYDAY("myday"),
        COVER("background"),
        GALLERY("others");
        private String title;

        ImageType(String title) {
            this.title = title;
        }
    }


}
