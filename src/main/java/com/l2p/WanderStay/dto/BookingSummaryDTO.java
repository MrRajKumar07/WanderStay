package com.l2p.WanderStay.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Data
public class BookingSummaryDTO {

    private UUID id;
    private String confirmationNumber;
    private String hotelName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;
    private BigDecimal totalAmount;
}