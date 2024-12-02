package com.example.quickmart.domain.product.dto.response;

public record ProductResponseDTO(String id, String name, String description, String category, Double price, Double stock, Double rating) {}
