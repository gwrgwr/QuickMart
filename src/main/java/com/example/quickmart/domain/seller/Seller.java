package com.example.quickmart.domain.seller;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.user.UserEntity;
import com.example.quickmart.domain.user.UserRole;
import jakarta.persistence.*;

import java.util.List;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "seller_id"))
public class Seller extends UserEntity {

    public Seller() {
        super.setRole(UserRole.SELLER);
    }

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products;

    private Integer salesQuantity = 0;

    private Double rating = 0.0;

    public Seller(String name, String username, String email, String password) {
        super(name, username, email, password, UserRole.SELLER);

    }
}
