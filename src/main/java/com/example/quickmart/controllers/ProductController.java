package com.example.quickmart.controllers;

import com.example.quickmart.domain.image.ImageDB;
import com.example.quickmart.domain.image.dto.ResponseImageDTO;
import com.example.quickmart.domain.product.dto.request.ProductRequestDTO;
import com.example.quickmart.domain.product.dto.response.ProductResponseDTO;
import com.example.quickmart.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Product", description = "Product API")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{sellerNickname}")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Get all products of a seller", summary = "Get all products of a seller")
    public List<ProductResponseDTO> getProducts(@PathVariable String sellerNickname) {
        return this.productService.getProductsBySeller(sellerNickname);
    }

    @GetMapping("{sellerNickname}/{productId}")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Get a product by id", summary = "Get a product by id")
    public ProductResponseDTO getProduct(@PathVariable String sellerNickname, @PathVariable String productId) {
        return this.productService.getProductBySeller(sellerNickname, productId);
    }

    @GetMapping("{sellerNickname}/{productId}/images")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Get all images of a product", summary = "Get all images of a product")
    public ResponseEntity<List<ResponseImageDTO>> getProductImages(@PathVariable String sellerNickname, @PathVariable String productId) {
        return ResponseEntity.ok().body(this.productService.getProductImages(sellerNickname, productId));
    }

    @GetMapping("{sellerNickname}/{productId}/image/{imageId}")
    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @Operation(description = "Get an image of a product", summary = "Get an image of a product")
    public ResponseEntity<byte[]> getFile(@PathVariable String sellerNickname, @PathVariable String productId, @PathVariable String imageId) {
        HttpHeaders headers = new HttpHeaders();
        ImageDB imageDB = this.productService.getProductImage(sellerNickname, productId, imageId);
        headers.setContentType(MediaType.valueOf(imageDB.getFileType()));
        headers.setContentDisposition(ContentDisposition.inline().filename(imageDB.getFilename()).build());
        return ResponseEntity.ok()
                .headers(headers)
                .body(imageDB.getData());
    }


    @PostMapping("{sellerNickname}")
    @PreAuthorize("hasAuthority('SCOPE_SELLER')")
    @Operation(description = "Add a product", summary = "Add a product")
    public ProductResponseDTO addProduct(
            @PathVariable String sellerNickname,
            @RequestBody ProductRequestDTO product
    ) {
        try {
            return this.productService.saveProduct(sellerNickname, product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("{sellerNickname}/{productId}/image")
    @PreAuthorize("hasAuthority('SCOPE_SELLER')")
    @Operation(description = "Add a product image", summary = "Add a product image")
    public void addProductImage(
            @PathVariable String sellerNickname,
            @PathVariable String productId,
            @RequestParam("images") MultipartFile[] image
    ) {
        try {
            this.productService.addProductImage(sellerNickname, productId, image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("{sellerNickname}/{productId}")
    @PreAuthorize("hasAuthority('SCOPE_SELLER')")
    @Operation(description = "Update a product", summary = "Update a product")
    public ProductResponseDTO updateProduct(@PathVariable String sellerNickname, @PathVariable String productId, @RequestBody ProductRequestDTO data) {
        return this.productService.updateProduct(sellerNickname, productId, data);
    }

    @DeleteMapping("{sellerNickname}/{productId}")
    @PreAuthorize("hasAuthority('SCOPE_SELLER')")
    @Operation(description = "Delete a product", summary = "Delete a product")
    public void deleteProduct(@PathVariable String sellerNickname, @PathVariable String productId) {
        this.productService.deleteProduct(sellerNickname, productId);
    }
}
