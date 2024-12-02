package com.example.quickmart.mapper;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.product.dto.request.ProductSaveDTO;
import com.example.quickmart.domain.product.dto.response.ProductResponseDTO;
import com.example.quickmart.domain.seller.Seller;

import java.util.List;

public class ProductMapper {
    public static ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getRating()
        );
    }

    public static ProductSaveDTO toProductSaveDTO(Product product) {
        return new ProductSaveDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory()
        );
    }

    public static List<ProductResponseDTO> toProductResponseDTOList(List<Product> productList) {
        return productList.stream().map(ProductMapper::toProductResponseDTO).toList();
    }

    public static Product toProduct(ProductSaveDTO productSaveDTO, Seller seller) {
        return new Product(
                productSaveDTO.name(),
                productSaveDTO.description(),
                productSaveDTO.price(),
                productSaveDTO.stock(),
                productSaveDTO.category(),
                seller
        );
    }
}
