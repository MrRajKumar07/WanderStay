package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.LoginRequest;
import com.l2p.WanderStay.dto.RegisterRequest;
import com.l2p.WanderStay.exception.WanderStayException;
import com.l2p.WanderStay.config.JwtUtil;
import com.l2p.WanderStay.dto.AuthResponseDTO;
import com.l2p.WanderStay.model.Role;
import com.l2p.WanderStay.model.User;
import com.l2p.WanderStay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new WanderStayException("Email already exists", HttpStatus.CONFLICT);
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return login(new LoginRequest(request.getEmail(), request.getPassword()));
    }

    @Override
    public AuthResponseDTO login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return AuthResponseDTO.builder()
                .accessToken(token)
                .role(user.getRole())
                .userId(user.getId())
                .email(user.getEmail())
                .build();
    }
}