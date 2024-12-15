package com.example.quickmart.mapper;

import com.example.quickmart.domain.image.ImageDB;
import com.example.quickmart.domain.image.dto.ResponseImageDTO;
import com.example.quickmart.domain.product.Product;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ImageMapper {
    public static ImageDB mapToImageDB(String fileName, String contentType, byte[] bytes, Product product) {
        return new ImageDB(fileName, contentType, bytes, product);
    }

    public static ResponseImageDTO mapToImageDB(ImageDB image, String productId, String sellerNickname) {
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/v1/seller/")
                .path(sellerNickname)
                .path("/product/")
                .path(productId)
                .path("/image/")
                .path(image.getId())
                .toUriString();
        return new ResponseImageDTO(image.getId(), image.getFilename(), image.getFileType(), fileDownloadUri);
    }
}
