package com.example.quickmart.domain.client.dto;

import com.example.quickmart.dto.LoginResponseDTO;

public record ClientLoginResponseDTO(LoginResponseDTO loginResponseDTO, String role) {
}
