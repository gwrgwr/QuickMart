package com.example.quickmart.service;

import com.example.quickmart.domain.product.Product;
import com.example.quickmart.domain.product.dto.request.ProductSaveDTO;
import com.example.quickmart.domain.product.dto.response.ProductResponseDTO;
import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.domain.seller.dto.request.SellerSaveDTO;
import com.example.quickmart.domain.seller.dto.request.SellerUpdateDTO;
import com.example.quickmart.domain.seller.dto.response.SellerResponseDTO;
import com.example.quickmart.exceptions.seller.SellerNotFoundException;
import com.example.quickmart.mapper.ProductMapper;
import com.example.quickmart.mapper.SellerMapper;
import com.example.quickmart.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final ProductService productService;

    public SellerService(SellerRepository sellerRepository, ProductService productService) {
        this.sellerRepository = sellerRepository;
        this.productService = productService;
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
        seller.setFullName(data.name());
        seller.setUsername(data.username());
        seller.setEmail(data.email());
        this.sellerRepository.save(seller);
        return SellerMapper.toSellerResponseDTO(seller);
    }

    public void deleteSeller(String sellerId) {
        this.sellerRepository.delete(this.getSellerById(sellerId));
    }

    // Product

    public ProductResponseDTO getProductByName(String nickname, String name) {
        Seller seller = this.getSellerByNickname(nickname);
        Product product = this.productService.getProductByName(name);
        validateProductBelongsToSeller(product, seller);
        return ProductMapper.toProductResponseDTO(product);
    }

    public List<ProductResponseDTO> getProducts(String nickname) {
        Seller seller = this.getSellerByNickname(nickname);
        List<Product> productList = this.productService.getProductsBySeller(seller);
        return ProductMapper.toProductResponseDTOList(productList);
    }

    public ProductResponseDTO addProduct(String nickname, ProductSaveDTO data) {
        Seller seller = this.getSellerByNickname(nickname);
        Product product = ProductMapper.toProduct(data, seller);
        validateProductBelongsToSeller(product, seller);
        return ProductMapper.toProductResponseDTO(this.productService.saveProduct(ProductMapper.toProductSaveDTO(product), seller));
    }

    public ProductResponseDTO updateProduct(String nickname, String productId, Product product) {
        Product existingProduct = this.productService.getProductById(productId);
        Seller seller = this.getSellerByNickname(nickname);
        validateProductBelongsToSeller(existingProduct, seller);
        return ProductMapper.toProductResponseDTO(this.productService.saveProduct(ProductMapper.toProductSaveDTO(product), seller));
    }

    public void deleteProduct(String nickname, String productId) {
        Product existingProduct = this.productService.getProductById(productId);
        Seller seller = this.getSellerByNickname(nickname);
        validateProductBelongsToSeller(existingProduct, seller);
        this.productService.deleteProduct(existingProduct, seller);
    }
}
