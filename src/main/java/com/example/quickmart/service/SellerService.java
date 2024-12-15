package com.example.quickmart.service;

import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.domain.seller.dto.request.SellerSaveDTO;
import com.example.quickmart.domain.seller.dto.request.SellerUpdateDTO;
import com.example.quickmart.domain.seller.dto.response.SellerResponseDTO;
import com.example.quickmart.exceptions.seller.SellerNotFoundException;
import com.example.quickmart.mapper.SellerMapper;
import com.example.quickmart.repositories.SellerRepository;
import com.example.quickmart.shared.utils.ReflectionUtils;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller getSellerByNickname(String nickname) {
        return this.sellerRepository.findByNickname(nickname).orElseThrow(SellerNotFoundException::new);
    }

    public Seller getSellerByEmailForLogin(String email) {
        return this.sellerRepository.findByEmail(email).orElse(null);
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
}
