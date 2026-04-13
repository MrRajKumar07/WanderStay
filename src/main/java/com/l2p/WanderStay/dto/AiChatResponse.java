package com.l2p.WanderStay.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AiChatResponse {
    private String reply;
    private LocalDateTime timestamp;
}