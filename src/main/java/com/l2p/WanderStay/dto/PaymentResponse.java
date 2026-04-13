package com.l2p.WanderStay.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private UUID id;
    private UUID bookingId;
    private BigDecimal amount;
    private String method; // Target for @Mapping(target = "method"...)
    private String status; // Target for @Mapping(target = "status"...)
    private String transactionId;
    private LocalDateTime paymentDate;
}