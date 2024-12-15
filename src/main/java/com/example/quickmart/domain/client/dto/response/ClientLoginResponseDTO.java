package com.example.quickmart.domain.client.dto.response;

import com.example.quickmart.shared.dto.LoginResponseDTO;

public record ClientLoginResponseDTO(LoginResponseDTO loginResponseDTO, String role) {
}
