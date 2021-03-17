package com.clone.fbclone.entities;

import org.springframework.security.core.GrantedAuthority;


/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */

public enum Roles implements GrantedAuthority {

    USER("users"),
    ADMIN("admin"),
    SUPERADMIN("superAdmin"),
    ALL("viewer");


    private String title;

    private Roles(String title) {
        this.title = title;
    }

    public static Roles fromTitle(String val) {
        if (val == null || val.isEmpty()) return null;
        for (Roles it : values())
            if (it.title == val) return it;
        return null;
    }

    @Override
    public String getAuthority() {
        return this.title;
    }
}
