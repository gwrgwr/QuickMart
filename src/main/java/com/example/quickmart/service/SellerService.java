package com.example.quickmart.service;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final ProductService productService;

    public SellerService(SellerRepository sellerRepository, ProductService productService) {
        this.sellerRepository = sellerRepository;
        this.productService = productService;
    }

    private void validateProductBelongsToSeller(Product product, Seller seller) {
        if (product.getSeller() != null && !product.getSeller().equals(seller)) {
            throw new IllegalArgumentException("Product does not belong to seller");
        }
    }

    public Seller getSellerByName(String name) {
        return this.sellerRepository.findByUsernameLike(name).orElseThrow();
    }

    public Seller getSellerByEmail(String email) {
        return this.sellerRepository.findByEmail(email).orElseThrow();
    }

    public Seller getSellerById(String id) {
        return this.sellerRepository.findById(id).orElseThrow();
    }

    public Seller saveSeller(Seller seller) {
        return this.sellerRepository.save(seller);
    }

    public void deleteSeller(String sellerId) {
        Seller seller = this.getSellerById(sellerId);
        this.sellerRepository.delete(seller);
    }

    public Product getProductByName(String sellerId, String name) {
        Seller seller = this.getSellerById(sellerId);
        Product product = this.productService.getProductByName(name);
        validateProductBelongsToSeller(product, seller);
        return product;
    }

    public List<Product> getProducts(String sellerId) {
        Seller seller = this.getSellerById(sellerId);
        return this.productService.getProductsBySeller(seller);
    }

    public Product addProduct(String sellerId, Product product) {
        Seller seller = this.getSellerById(sellerId);
        validateProductBelongsToSeller(product, seller);
        return this.productService.saveProduct(product, seller);
    }

    public Product updateProduct(String sellerId, String productId, Product product) {
        Product existingProduct = this.productService.getProductById(productId);
        Seller seller = this.getSellerById(sellerId);
        validateProductBelongsToSeller(existingProduct, seller);
        return this.productService.saveProduct(product, seller);
    }

    public void deleteProduct(String sellerId, String productId) {
        Product existingProduct = this.productService.getProductById(productId);
        Seller seller = this.getSellerById(sellerId);
        validateProductBelongsToSeller(existingProduct, seller);
        this.productService.deleteProduct(existingProduct, seller);
    }
}
