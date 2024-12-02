package com.example.quickmart.domain.seller.dto.response;

import com.example.quickmart.dto.LoginResponseDTO;

public record SellerLoginResponseDTO(LoginResponseDTO loginResponse, String role, String nickname) {
}
