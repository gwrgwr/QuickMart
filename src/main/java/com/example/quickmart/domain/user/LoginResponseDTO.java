package com.example.quickmart.domain.user;

public record LoginResponseDTO(String token, String id, String email, String name) {
}
