package com.l2p.WanderStay.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookingResponse {

    private UUID id;
    private UUID userId;
    private Long roomId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private String status;
    private BigDecimal totalAmount;

    private String confirmationNumber;
}