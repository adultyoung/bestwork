package by.bestwork.dto;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    STUDENT,
    MODERATOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
