package com.example.quickmart.domain.seller.dto.response;

import com.example.quickmart.shared.dto.LoginResponseDTO;

public record SellerLoginResponseDTO(LoginResponseDTO loginResponse, String role, String nickname) {
}
