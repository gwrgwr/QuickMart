package com.example.quickmart.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    SELLER("seller"),
    CLIENT("client"),
    GUEST("guest");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
