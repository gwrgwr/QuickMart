package com.example.quickmart.service;

import com.example.quickmart.domain.image.ImageDB;
import com.example.quickmart.domain.image.dto.ResponseImageDTO;
import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.product.dto.request.ProductRequestDTO;
import com.example.quickmart.domain.product.dto.response.ProductResponseDTO;
import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.exceptions.product.ProductAlreadyExistsException;
import com.example.quickmart.exceptions.product.ProductNotFoundException;
import com.example.quickmart.mapper.ImageMapper;
import com.example.quickmart.mapper.ProductMapper;
import com.example.quickmart.repositories.ProductRepository;
import com.example.quickmart.shared.utils.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SellerService sellerService;
    private final ImageService imageService;

    public ProductService(ProductRepository productRepository, SellerService sellerService, ImageService imageService) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
        this.imageService = imageService;
    }

    private void validateProductBelongsToSeller(Product product, Seller seller) {
        if (product.getSeller() != null && !product.getSeller().equals(seller)) {
            throw new IllegalArgumentException("Product does not belong to seller");
        }
    }

    public List<ProductResponseDTO> getProductsBySeller(String nickname) {
        Seller seller = this.sellerService.getSellerByNickname(nickname);
        return ProductMapper.toProductResponseDTOList(this.productRepository.findBySeller(seller));
    }

    public Product getProductById(String productId) {
        return this.productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public ProductResponseDTO getProductBySeller(String sellerId, String productId) {
        Seller seller = this.sellerService.getSellerById(sellerId);
        Product product = this.productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        this.validateProductBelongsToSeller(product, seller);
        return ProductMapper.toProductResponseDTO(product);
    }

    public ProductResponseDTO saveProduct(String nickname, ProductRequestDTO data) {
        Seller seller = this.sellerService.getSellerByNickname(nickname);

        Product product = ProductMapper.toProduct(data, seller);

        boolean productExists = this.productRepository.existsByNameAndSeller(data.name(), seller);
        if (productExists) {
            throw new ProductAlreadyExistsException();
        }

        // Salvar e retornar a resposta
        return ProductMapper.toProductResponseDTO(this.productRepository.save(product));
    }

    public void addProductImage(String nickname, String productId, MultipartFile[] images) throws IOException {
        Seller seller = this.sellerService.getSellerByNickname(nickname);
        Product product = getProductById(productId);
        this.validateProductBelongsToSeller(product, seller);
        product.setImage(this.imageService.storeMultiple(images, product));
        this.productRepository.save(product);
    }

    public ImageDB getProductImage(String nickname, String productId, String imageId) {
        Seller seller = this.sellerService.getSellerByNickname(nickname);
        Product product = getProductById(productId);
        this.validateProductBelongsToSeller(product, seller);
        return product.getImage().stream().filter(i -> i.getId().equals(imageId)).findFirst().orElseThrow();

    }

    public List<ResponseImageDTO> getProductImages(String nickname, String productId) {
        Seller seller = this.sellerService.getSellerByNickname(nickname);
        Product product = getProductById(productId);
        this.validateProductBelongsToSeller(product, seller);
        List<ImageDB> images = product.getImage();
        List<ResponseImageDTO> responseList = new ArrayList<>();
        for (ImageDB image : images) {
            responseList.add(ImageMapper.mapToImageDB(image, productId, nickname));
        }
        return responseList;
    }

    public ProductResponseDTO updateProduct(String nickname, String productId, ProductRequestDTO data) {
        Seller seller = this.sellerService.getSellerByNickname(nickname);
        Product product = this.productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        this.validateProductBelongsToSeller(product, seller);
        ReflectionUtils.updateEntitiesFields(product, ProductMapper.toProduct(data, seller));
        return ProductMapper.toProductResponseDTO(this.productRepository.save(product));
    }

    public void deleteProduct(String nickname, String productId) {
        Seller seller = this.sellerService.getSellerByNickname(nickname);
        Product product = this.productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        this.validateProductBelongsToSeller(product, seller);
        this.productRepository.delete(product);
    }
}
