package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.UserDTO;

import java.util.UUID;

public interface UserService {
    UserDTO getProfile(String email);
    UserDTO getById(UUID id);
}