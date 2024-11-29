package com.example.quickmart.domain.product;

import com.example.quickmart.domain.seller.Seller;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private String id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @Positive(message = "Price must be positive")
    private Double price;

    @Positive(message = "Stock must be positive")
    private Double stock;

    @NotNull(message = "Category cannot be null")
    private String category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Positive(message = "Rating must be positive")
    private Double rating;
}
