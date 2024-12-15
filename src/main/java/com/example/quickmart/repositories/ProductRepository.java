package com.example.quickmart.repositories;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findBySeller(Seller seller);

    Optional<Product> findByNameLike(String name);

    boolean existsByNameAndSeller(String name, Seller seller);
}
