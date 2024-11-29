package com.example.quickmart.repositories;

import com.example.quickmart.domain.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, String> {
    Optional<Seller> findByEmail(String email);

    Optional<Seller> findByUsernameLike(String username);
}
