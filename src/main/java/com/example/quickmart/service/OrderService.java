package com.example.quickmart.service;

import com.example.quickmart.domain.order.OrderEntity;
import com.example.quickmart.domain.order.dto.request.OrderRequestDTO;
import com.example.quickmart.mapper.OrderMapper;
import com.example.quickmart.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public OrderEntity createOrder(OrderRequestDTO order) {
        return orderRepository.save(OrderMapper.toEntity(order));
    }

    public OrderEntity addProductToOrder(String orderId, String productId) {
        OrderEntity order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.getProductsId().add(productId);
            return orderRepository.save(order);
        }
        return null;
    }

    public OrderEntity updateOrder(String id, OrderEntity order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }
}
