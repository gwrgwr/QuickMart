package com.example.quickmart.controllers;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.domain.seller.dto.request.SellerSaveDTO;
import com.example.quickmart.domain.seller.dto.request.SellerUpdateDTO;
import com.example.quickmart.domain.seller.dto.response.SellerResponseDTO;
import com.example.quickmart.mapper.SellerMapper;
import com.example.quickmart.service.SellerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<SellerResponseDTO> getSellerByName(@PathVariable String sellerName) {
        return ResponseEntity.ok(this.sellerService.getSellerByName(sellerName));
    }

    @GetMapping("email/{sellerEmail}")
    public ResponseEntity<SellerResponseDTO> getSellerByEmail(@PathVariable String sellerEmail) {
        return ResponseEntity.ok(SellerMapper.toSellerResponseDTO(this.sellerService.getSellerByEmail(sellerEmail)));
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<SellerResponseDTO> addSeller(@RequestBody SellerSaveDTO data) {
        return ResponseEntity.ok(this.sellerService.saveSeller(data));
    }

    @PutMapping("{sellerId}")
    @PreAuthorize("hasAuthority('SCOPE_SELLER')")
    public ResponseEntity<SellerResponseDTO> updateSeller(@PathVariable String sellerId, @RequestBody SellerUpdateDTO data) {
        return ResponseEntity.ok(this.sellerService.updateSeller(sellerId, data));
    }

    @DeleteMapping("{sellerId}")
    @PreAuthorize("hasAuthority('SCOPE_SELLER')")
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
