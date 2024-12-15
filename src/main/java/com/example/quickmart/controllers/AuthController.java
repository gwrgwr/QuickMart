package com.example.quickmart.controllers;

import com.example.quickmart.domain.user.dto.request.LoginRequestDTO;
import com.example.quickmart.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Auth API")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    @Operation(description = "Login", summary = "Login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO data) {
        return ResponseEntity.ok(authService.login(data));
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
