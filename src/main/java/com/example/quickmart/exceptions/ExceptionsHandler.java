package com.example.quickmart.exceptions;

import com.example.quickmart.dto.HttpResponseExceptionDTO;
import com.example.quickmart.exceptions.seller.SellerAlreadyExists;
import com.example.quickmart.exceptions.seller.SellerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<HttpResponseExceptionDTO> handleSellerNotFoundException(SellerNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HttpResponseExceptionDTO(request.getRequestURI(), HttpStatus.NOT_FOUND, "Not Found", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(SellerAlreadyExists.class)
    public ResponseEntity<HttpResponseExceptionDTO> handleSellerAlreadyExists(SellerAlreadyExists e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new HttpResponseExceptionDTO(request.getRequestURI(), HttpStatus.CONFLICT, "Conflict", e.getMessage(), LocalDateTime.now()));
    }
}