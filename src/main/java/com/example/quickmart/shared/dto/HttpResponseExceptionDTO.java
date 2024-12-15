package com.example.quickmart.shared.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record HttpResponseExceptionDTO(String path, HttpStatus status, String error, String message, LocalDateTime timestamp) {}

