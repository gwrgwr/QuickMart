package com.example.quickmart.domain.product.dto.request;

public record ProductSaveDTO(String name, String description, Double price, Double stock, String category) {
}
