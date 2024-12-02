package com.example.quickmart.exceptions.seller;

public class SellerNotFoundException extends RuntimeException{
    public SellerNotFoundException(String message) {
        super(message);
    }

    public SellerNotFoundException() {
        super("Seller not found");
    }
}
