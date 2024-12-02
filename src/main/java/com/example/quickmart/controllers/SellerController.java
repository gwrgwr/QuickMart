package com.example.quickmart.controllers;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.product.dto.request.ProductRequestDTO;
import com.example.quickmart.domain.product.dto.response.ProductResponseDTO;
import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.domain.seller.dto.request.SellerSaveDTO;
import com.example.quickmart.domain.seller.dto.request.SellerUpdateDTO;
import com.example.quickmart.domain.seller.dto.response.SellerResponseDTO;
import com.example.quickmart.mapper.SellerMapper;
import com.example.quickmart.service.SellerService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "Get all sellers", summary = "Get all sellers")
    public Seller getSeller(@PathVariable String sellerId) {
        return this.sellerService.getSellerById(sellerId);
    }

    @GetMapping("name/{sellerNickname}")
    @Operation(description = "Get a seller by nickname", summary = "Get a seller by nickname")
    public ResponseEntity<SellerResponseDTO> getSellerByName(@PathVariable String sellerNickname) {
        return ResponseEntity.ok(SellerMapper.toSellerResponseDTO(this.sellerService.getSellerByNickname(sellerNickname)));
    }

    @GetMapping("email/{sellerEmail}")
    @Operation(description = "Get a seller by email", summary = "Get a seller by email")
    public ResponseEntity<SellerResponseDTO> getSellerByEmail(@PathVariable String sellerEmail) {
        return ResponseEntity.ok(this.sellerService.getSellerByEmailForSearch(sellerEmail));
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    @Operation(description = "Add a new seller", summary = "Add a new seller")
    public ResponseEntity<SellerResponseDTO> addSeller(@RequestBody SellerSaveDTO data) {
        return ResponseEntity.ok(this.sellerService.saveSeller(data));
    }

    @PutMapping("{sellerId}")
    @PreAuthorize("hasAuthority('SCOPE_SELLER')")
    @Operation(description = "Update a seller", summary = "Update a seller")
    public ResponseEntity<SellerResponseDTO> updateSeller(@PathVariable String sellerId, @RequestBody SellerUpdateDTO data) {
        return ResponseEntity.ok(this.sellerService.updateSeller(sellerId, data));
    }

    @DeleteMapping("{sellerId}")
    @PreAuthorize("hasAuthority('SCOPE_SELLER')")
    @Operation(description = "Delete a seller", summary = "Delete a seller")
    public void deleteSeller(@PathVariable String sellerId) {
        this.sellerService.deleteSeller(sellerId);
    }


// Products


    @GetMapping("{nickname}/product")
    @PreAuthorize("hasAnyAuthority('SCOPE_SELLER')")
    @Operation(description = "Get all products of a seller", summary = "Get all products of a seller")
    public List<ProductResponseDTO> getProducts(@PathVariable String nickname) {
        return this.sellerService.getProductsBySeller(nickname);
    }

    @PostMapping("{nickname}/product")
    @PreAuthorize("hasAnyAuthority('SCOPE_SELLER')")
    @Operation(description = "Add a product", summary = "Add a product")
    public ProductResponseDTO addProduct(@PathVariable String nickname, @RequestBody ProductRequestDTO product) {
        return this.sellerService.saveProduct(nickname, product);
    }

    @PutMapping("{nickname}/product/{productId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_SELLER')")
    @Operation(description = "Update a product", summary = "Update a product")
    public ProductResponseDTO updateProduct(@PathVariable String nickname, @PathVariable String productId, @RequestBody ProductRequestDTO data) {
        return this.sellerService.updateProduct(nickname, productId, data);
    }

    @DeleteMapping("{nickname}/product/{productId}")
    @PreAuthorize("hasAnyAuthority('SCOPE_SELLER')")
    @Operation(description = "Delete a product", summary = "Delete a product")
    public void deleteProduct(@PathVariable String nickname, @PathVariable String productId) {
        this.sellerService.deleteProduct(nickname, productId);
    }

}
