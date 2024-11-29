package com.example.quickmart.controllers;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.service.SellerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seller")
@Tag(name = "Seller", description = "Seller API")
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("id/{sellerId}")
    public Seller getSeller(@PathVariable String sellerId) {
        return this.sellerService.getSellerById(sellerId);
    }

    @GetMapping("name/{sellerName}")
    public Seller getSellerByName(@PathVariable String sellerName) {
        return this.sellerService.getSellerByName(sellerName);
    }

    @GetMapping("email/{sellerEmail}")
    public Seller getSellerByEmail(@PathVariable String sellerEmail) {
        return this.sellerService.getSellerByEmail(sellerEmail);
    }

    @PostMapping
    public Seller addSeller(@RequestBody Seller seller) {
        return this.sellerService.saveSeller(seller);
    }

    @PutMapping("{sellerId}")
    public Seller updateSeller(@PathVariable String sellerId, @RequestBody Seller seller) {
        return this.sellerService.saveSeller(seller);
    }

    @DeleteMapping("{sellerId}")
    public void deleteSeller(@PathVariable String sellerId) {
        this.sellerService.deleteSeller(sellerId);
    }





// Products



    @GetMapping("{sellerId}/products")
    public List<Product> getProducts(@PathVariable String sellerId) {
        return this.sellerService.getProducts(sellerId);
    }

    @GetMapping("{sellerId}/product/{productName}")
    public Product getProductByName(@PathVariable String sellerId, @PathVariable String productName) {
        return this.sellerService.getProductByName(sellerId, productName);
    }

    @PostMapping("{sellerId}/product")
    public Product addProduct(@PathVariable String sellerId, @RequestBody Product product) {
        return this.sellerService.addProduct(sellerId, product);
    }

    @PutMapping("{sellerId}/product/{productId}")
    public Product updateProduct(@PathVariable String sellerId, @PathVariable String productId, @RequestBody Product product) {
        return this.sellerService.updateProduct(sellerId, productId, product);
    }

    @DeleteMapping("{sellerId}/product/{productId}")
    public void deleteProduct(@PathVariable String sellerId, @PathVariable String productId) {
        this.sellerService.deleteProduct(sellerId, productId);
    }

}
