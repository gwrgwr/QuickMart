package com.example.quickmart.domain.user.dto.response;

public record LoginResponseDTO(String token, String id, String email, String name) {
}
