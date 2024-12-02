package com.example.quickmart.exceptions.seller;

public class SellerAlreadyExists extends RuntimeException{
    public SellerAlreadyExists(String message) {
        super(message);
    }

    public SellerAlreadyExists() {
        super("Seller already exists");
    }
}
