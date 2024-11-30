package com.example.quickmart.mapper;

import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.domain.seller.dto.request.SellerSaveDTO;
import com.example.quickmart.domain.seller.dto.request.SellerUpdateDTO;
import com.example.quickmart.domain.seller.dto.response.SellerResponseDTO;

public class SellerMapper {
    public static SellerResponseDTO toSellerResponseDTO(Seller seller) {
        return new SellerResponseDTO(seller.getId(), seller.getName(), seller.getUsername(), seller.getEmail(), seller.getRole().getRole());
    }

    public static SellerUpdateDTO toSellerUpdateDTO(Seller seller) {
        return new SellerUpdateDTO(seller.getName(), seller.getUsername(), seller.getEmail());
    }

    public static SellerSaveDTO toSellerSaveDTO(Seller seller) {
        return new SellerSaveDTO(seller.getName(), seller.getUsername(), seller.getEmail(), seller.getPassword());
    }
}
