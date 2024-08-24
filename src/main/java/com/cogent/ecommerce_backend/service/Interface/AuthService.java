package com.cogent.ecommerce_backend.service.Interface;

import com.cogent.ecommerce_backend.payload.LoginDto;
import com.cogent.ecommerce_backend.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}