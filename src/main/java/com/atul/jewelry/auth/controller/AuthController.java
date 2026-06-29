package com.atul.jewelry.auth.controller;

import com.atul.jewelry.auth.dto.request.LoginRequest;
import com.atul.jewelry.auth.dto.request.RegisterRequest;
import com.atul.jewelry.auth.dto.response.AuthResponse;
import com.atul.jewelry.auth.dto.response.LoginResponse;
import com.atul.jewelry.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        return authService.login(request);

    }
}