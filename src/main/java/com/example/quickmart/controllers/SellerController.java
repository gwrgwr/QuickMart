package com.example.quickmart.controllers;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.product.dto.request.ProductSaveDTO;
import com.example.quickmart.domain.product.dto.response.ProductResponseDTO;
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

    @GetMapping("name/{sellerNickname}")
    public ResponseEntity<SellerResponseDTO> getSellerByName(@PathVariable String sellerNickname) {
        return ResponseEntity.ok(SellerMapper.toSellerResponseDTO(this.sellerService.getSellerByNickname(sellerNickname)));
    }

    @GetMapping("email/{sellerEmail}")
    public ResponseEntity<SellerResponseDTO> getSellerByEmail(@PathVariable String sellerEmail) {
        return ResponseEntity.ok(this.sellerService.getSellerByEmailForSearch(sellerEmail));
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


    @GetMapping("{nickname}/product")
    public List<ProductResponseDTO> getProducts(@PathVariable String nickname) {
        return this.sellerService.getProducts(nickname);
    }

    @GetMapping("{nickname}/product/{productName}")
    public ProductResponseDTO getProductByName(@PathVariable String nickname, @PathVariable String productName) {
        return this.sellerService.getProductByName(nickname, productName);
    }

    @PostMapping("{nickname}/product")
    public ProductResponseDTO addProduct(@PathVariable String nickname, @RequestBody ProductSaveDTO product) {
        return this.sellerService.addProduct(nickname, product);
    }

    @PutMapping("{nickname}/product/{productId}")
    public ProductResponseDTO updateProduct(@PathVariable String nickname, @PathVariable String productId, @RequestBody Product product) {
        return this.sellerService.updateProduct(nickname, productId, product);
    }

    @DeleteMapping("{nickname}/product/{productId}")
    public void deleteProduct(@PathVariable String nickname, @PathVariable String productId) {
        this.sellerService.deleteProduct(nickname, productId);
    }

}
