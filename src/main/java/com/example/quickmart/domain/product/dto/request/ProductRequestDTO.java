package com.example.quickmart.domain.product.dto.request;

public record ProductRequestDTO(String name, String description, Double price, Double stock, String category) {
}
