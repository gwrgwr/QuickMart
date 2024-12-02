package com.example.quickmart.mapper;

import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.domain.seller.dto.request.SellerUpdateDTO;
import com.example.quickmart.domain.seller.dto.response.SellerResponseDTO;

public class SellerMapper {
    public static SellerResponseDTO toSellerResponseDTO(Seller seller) {
        return new SellerResponseDTO(seller.getId(), seller.getFullName(), seller.getUsername(), seller.getNickname(), seller.getEmail(), seller.getRole().getRole());
    }

    public static SellerUpdateDTO toSellerUpdateDTO(Seller seller) {
        return new SellerUpdateDTO(seller.getFullName(), seller.getUsername(), seller.getEmail());
    }
}
