package com.atul.jewelry.auth.service;

import com.atul.jewelry.auth.dto.request.LoginRequest;
import com.atul.jewelry.auth.dto.request.RegisterRequest;
import com.atul.jewelry.auth.dto.response.AuthResponse;
import com.atul.jewelry.auth.dto.response.LoginResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}