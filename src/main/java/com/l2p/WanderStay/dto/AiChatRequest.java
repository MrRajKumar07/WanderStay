package com.l2p.WanderStay.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AiChatRequest {
    @NotBlank(message = "Message cannot be empty")
    private String message;
}
