package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.AuthResponseDTO;
import com.l2p.WanderStay.dto.LoginRequest;
import com.l2p.WanderStay.dto.RegisterRequest;

public interface AuthService {
    AuthResponseDTO register(RegisterRequest request);
    AuthResponseDTO login(LoginRequest request);
}