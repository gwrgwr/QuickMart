package com.example.quickmart.service;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.product.dto.request.ProductRequestDTO;
import com.example.quickmart.domain.product.dto.response.ProductResponseDTO;
import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.domain.seller.dto.request.SellerSaveDTO;
import com.example.quickmart.domain.seller.dto.request.SellerUpdateDTO;
import com.example.quickmart.domain.seller.dto.response.SellerResponseDTO;
import com.example.quickmart.exceptions.seller.SellerNotFoundException;
import com.example.quickmart.mapper.ProductMapper;
import com.example.quickmart.mapper.SellerMapper;
import com.example.quickmart.repositories.ProductRepository;
import com.example.quickmart.repositories.SellerRepository;
import com.example.quickmart.utils.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    public SellerService(SellerRepository sellerRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    private void validateProductBelongsToSeller(Product product, Seller seller) {
        if (product.getSeller() != null && !product.getSeller().equals(seller)) {
            throw new IllegalArgumentException("Product does not belong to seller");
        }
    }

    public Seller getSellerByNickname(String nickname) {
        return this.sellerRepository.findByNickname(nickname).orElseThrow(SellerNotFoundException::new);
    }

    public Seller getSellerByEmailForLogin(String email) {
        return this.sellerRepository.findByEmail(email).orElse(null);
    }

    public SellerResponseDTO getSellerByEmailForSearch(String email) {
        return this.sellerRepository.findByEmail(email).map(SellerMapper::toSellerResponseDTO).orElseThrow(SellerNotFoundException::new);
    }

    public Seller getSellerById(String id) {
        return this.sellerRepository.findById(id).orElseThrow(SellerNotFoundException::new);
    }

    public SellerResponseDTO saveSeller(SellerSaveDTO data) {
        Seller seller = new Seller(data.name(), data.username(), data.email(), data.password(), data.nickname());
        this.sellerRepository.save(seller);
        return SellerMapper.toSellerResponseDTO(seller);
    }

    public SellerResponseDTO updateSeller(String sellerId, SellerUpdateDTO data) {
        Seller seller = this.getSellerById(sellerId);
        ReflectionUtils.updateEntitiesFields(seller, SellerMapper.toSeller(data));
        return SellerMapper.toSellerResponseDTO(seller);
    }

    public void deleteSeller(String sellerId) {
        this.sellerRepository.delete(this.getSellerById(sellerId));
    }

    // Product

    public List<ProductResponseDTO> getProductsBySeller(String nickname) {
        Seller seller = this.getSellerByNickname(nickname);
        return ProductMapper.toProductResponseDTOList(this.productRepository.findBySeller(seller));
    }

    public ProductResponseDTO getProductBySeller(String sellerId, String productId) {
        Seller seller = this.getSellerById(sellerId);
        Product product = this.productRepository.findById(productId).orElseThrow(SellerNotFoundException::new);
        this.validateProductBelongsToSeller(product, seller);
        return ProductMapper.toProductResponseDTO(product);
    }

    public ProductResponseDTO saveProduct(String nickname, ProductRequestDTO data) {
        Seller seller = this.getSellerByNickname(nickname);
        Product product = ProductMapper.toProduct(data, seller);
        return ProductMapper.toProductResponseDTO(this.productRepository.save(product));
    }

    public ProductResponseDTO updateProduct(String nickname, String productId, ProductRequestDTO data) {
        Seller seller = this.getSellerByNickname(nickname);
        Product product = this.productRepository.findById(productId).orElseThrow(SellerNotFoundException::new);
        this.validateProductBelongsToSeller(product, seller);
        ReflectionUtils.updateEntitiesFields(product, ProductMapper.toProduct(data, seller));
        return ProductMapper.toProductResponseDTO(this.productRepository.save(product));
    }

    public void deleteProduct(String nickname, String productId) {
        Seller seller = this.getSellerByNickname(nickname);
        Product product = this.productRepository.findById(productId).orElseThrow(SellerNotFoundException::new);
        this.validateProductBelongsToSeller(product, seller);
        this.productRepository.delete(product);
    }
}
