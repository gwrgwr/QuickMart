package com.example.quickmart.controllers;

import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.domain.seller.dto.request.SellerSaveDTO;
import com.example.quickmart.domain.seller.dto.request.SellerUpdateDTO;
import com.example.quickmart.domain.seller.dto.response.SellerResponseDTO;
import com.example.quickmart.mapper.SellerMapper;
import com.example.quickmart.service.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller")
@Tag(name = "Seller", description = "Seller API")
@AllArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    @GetMapping("id/{sellerId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @Operation(description = "Get all sellers", summary = "Get all sellers")
    public Seller getSeller(@PathVariable String sellerId) {
        return this.sellerService.getSellerById(sellerId);
    }

    @GetMapping("name/{sellerNickname}")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Get a seller by nickname", summary = "Get a seller by nickname")
    public ResponseEntity<SellerResponseDTO> getSellerByName(@PathVariable String sellerNickname) {
        return ResponseEntity.ok(SellerMapper.toSellerResponseDTO(this.sellerService.getSellerByNickname(sellerNickname)));
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

}
