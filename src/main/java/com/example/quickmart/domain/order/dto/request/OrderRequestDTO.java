package com.example.quickmart.domain.order.dto.request;

import java.util.List;

public record OrderRequestDTO(String customerId, List<String> productsId, double totalPrice) {
}
