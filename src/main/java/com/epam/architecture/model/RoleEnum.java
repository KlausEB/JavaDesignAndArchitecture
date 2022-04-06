package com.epam.architecture.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Set;

public enum RoleEnum implements Serializable {
    USER,
    ADMIN;

    public Set<SimpleGrantedAuthority> getAthorities() {
        return Set.of(new SimpleGrantedAuthority(this.name()));
    }
}
