package com.l2p.WanderStay.dto;

import lombok.*;
import java.util.UUID;

import com.l2p.WanderStay.model.Role;

@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";
    private Role role;
    private UUID userId;
    private String email;
}