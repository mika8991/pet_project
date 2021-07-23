package ru.goodbadnews.rest.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class Role implements GrantedAuthority {
    public Role() {
    }

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
