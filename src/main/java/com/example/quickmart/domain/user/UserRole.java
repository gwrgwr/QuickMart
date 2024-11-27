package com.example.quickmart.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    CLIENT("client"),
    SELLER("seller"),
    GUEST("guest");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}
