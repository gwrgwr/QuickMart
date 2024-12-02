package com.example.quickmart.domain.client;

import com.example.quickmart.domain.user.UserEntity;
import com.example.quickmart.domain.user.UserRole;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
public class Client extends UserEntity {

    public Client() {
        super.setRole(UserRole.CLIENT);
    }


}
