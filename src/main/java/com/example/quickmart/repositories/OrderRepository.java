package com.example.quickmart.repositories;

import com.example.quickmart.domain.order.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

}
