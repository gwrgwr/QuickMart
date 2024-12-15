package com.example.quickmart.controllers;

import com.example.quickmart.domain.order.OrderEntity;
import com.example.quickmart.domain.order.dto.request.OrderRequestDTO;
import com.example.quickmart.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@Tag(name = "Order", description = "Order API")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("{orderId}")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Get an order by id", summary = "Get an order by id")
    public OrderEntity getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Create a new order", summary = "Create a new order")
    public OrderEntity createOrder(@RequestBody OrderRequestDTO order) {
        return orderService.createOrder(order);
    }

    @PutMapping("{orderId}/add-product/")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Add a product to an order", summary = "Add a product to an order")
    public OrderEntity addProductToOrder(@PathVariable String orderId, @RequestBody String productId) {
        return orderService.addProductToOrder(orderId, productId);
    }

    @PutMapping("{orderId}")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Update an order", summary = "Update an order")
    public OrderEntity updateOrder(@PathVariable String orderId, @RequestBody OrderEntity order) {
        return orderService.updateOrder(orderId, order);
    }

    @DeleteMapping("{orderId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    @Operation(description = "Delete an order", summary = "Delete an order")
    public void deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
    }
}
