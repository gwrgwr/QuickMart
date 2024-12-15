package com.example.quickmart.domain.client;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.user.UserEntity;
import com.example.quickmart.domain.user.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
public class Client extends UserEntity {


    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Product> buyedProducts;

    public Client(String fullName, String username, String email, String password, String nickname) {
        super(fullName, username, email, password, nickname, UserRole.CLIENT);
    }
}
