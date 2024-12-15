package com.example.quickmart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    private String id;

    private String customerId;

    private List<String> productsId;

    private double totalPrice = 0;

    public OrderEntity(String customerId, List<String> productsId) {
        this.customerId = customerId;
        this.productsId = productsId;
    }
}
