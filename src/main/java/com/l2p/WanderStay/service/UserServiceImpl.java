package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.UserDTO;
import com.l2p.WanderStay.exception.WanderStayException;
import com.l2p.WanderStay.mapper.UserMapper;
import com.l2p.WanderStay.model.User;
import com.l2p.WanderStay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO getProfile(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new WanderStayException("User not found", HttpStatus.NOT_FOUND));
        return userMapper.toDTO(user);
    }
    @Override
    public UserDTO getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new WanderStayException("User not found", HttpStatus.NOT_FOUND));
        return userMapper.toDTO(user);
    }
}