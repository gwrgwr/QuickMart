package com.example.quickmart.service;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.product.dto.request.ProductSaveDTO;
import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(String id) {
        return this.productRepository.findById(id).orElseThrow();
    }

    public Product getProductByName(String name) {
        return this.productRepository.findByNameLike(name).orElseThrow();
    }

    public Product saveProduct(ProductSaveDTO data, Seller seller) {
        return this.productRepository.save(new Product(data.name(), data.description(), data.price(), data.stock(), data.category(), seller));
    }

    public void deleteProduct(Product product, Seller seller) {
        if (!product.getSeller().equals(seller)) {
            throw new IllegalArgumentException("Product does not belong to seller");
        }
        this.productRepository.delete(product);
    }

    public List<Product> getProductsBySeller(Seller seller) {
        return this.productRepository.findBySeller(seller);
    }
}
