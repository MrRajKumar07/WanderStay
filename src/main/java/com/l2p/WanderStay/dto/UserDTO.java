package com.l2p.WanderStay.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

import com.l2p.WanderStay.model.Role;

@Data 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
public class UserDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}