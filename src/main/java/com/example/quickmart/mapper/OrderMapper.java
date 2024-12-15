package com.example.quickmart.mapper;

import com.example.quickmart.domain.order.OrderEntity;
import com.example.quickmart.domain.order.dto.request.OrderRequestDTO;

public class OrderMapper {
    public static OrderEntity toEntity(OrderRequestDTO orderRequestDTO) {
        return new OrderEntity(orderRequestDTO.customerId(), orderRequestDTO.productsId());
    }
}
