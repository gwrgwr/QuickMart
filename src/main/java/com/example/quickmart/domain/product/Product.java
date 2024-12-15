package com.example.quickmart.domain.product;

import com.example.quickmart.domain.client.Client;
import com.example.quickmart.domain.image.ImageDB;
import com.example.quickmart.domain.seller.Seller;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @Column(unique = true)
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
    @JsonIgnore
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @Positive(message = "Rating must be positive")
    private Double rating;

    @OneToMany(mappedBy = "product")
    private List<ImageDB> image;

    public Product(String name, String description, Double price, Double stock, String category, Seller seller) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.seller = seller;
        this.rating = 0.0;
    }
}
