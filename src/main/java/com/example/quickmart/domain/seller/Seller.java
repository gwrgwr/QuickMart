package com.example.quickmart.domain.seller;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.user.UserEntity;
import com.example.quickmart.domain.user.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "seller_id"))
public class Seller extends UserEntity {

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;
    private final Integer salesQuantity = 0;
    private final Double rating = 0.0;
    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String nickname;

    public Seller() {
        super.setRole(UserRole.SELLER);
    }

    public Seller(String fullName, String username, String email, String password, String nickname) {
        super(fullName, username, email, password, UserRole.SELLER);
        this.nickname = nickname;
    }
}
