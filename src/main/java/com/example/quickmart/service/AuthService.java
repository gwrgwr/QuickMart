package com.example.quickmart.service;

import com.example.quickmart.domain.client.Client;
import com.example.quickmart.domain.seller.Seller;
import com.example.quickmart.domain.user.LoginRequestDTO;
import com.example.quickmart.domain.user.LoginResponseDTO;
import com.example.quickmart.domain.user.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final TokenService tokenService;
    private final ClientService clientService;
    private final SellerService sellerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(TokenService tokenService, ClientService clientService, SellerService sellerService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tokenService = tokenService;
        this.clientService = clientService;
        this.sellerService = sellerService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private UserEntity findUserByEmail(String email) {
        Client client = clientService.getClientByEmail(email);
        if (client != null) {
            return client;
        }

        Seller seller = sellerService.getSellerByEmail(email);
        if (seller != null) {
            return seller;
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }

    public LoginResponseDTO login(LoginRequestDTO data) {
        UserEntity user = findUserByEmail(data.username());
        if (!bCryptPasswordEncoder.matches(data.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        String token = tokenService.generateLoginToken(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginResponseDTO(token, user.getId(), user.getEmail(), user.getName());
    }
}
