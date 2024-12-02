package com.example.quickmart.domain.image;

import com.example.quickmart.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDB {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "image_id")
    private String id;

    private String filename;

    private String fileType;

    @Lob
    private byte[] data;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public ImageDB(String filename, String fileType, byte[] data, Product product) {
        this.filename = filename;
        this.fileType = fileType;
        this.data = data;
        this.product = product;
    }
}
