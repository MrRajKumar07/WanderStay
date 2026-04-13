package com.l2p.WanderStay.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookingRequest {

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Room ID is required")
    private Long roomId;

    @NotNull(message = "Check-in date is required")
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date is required")
    private LocalDate checkOutDate;

    @NotBlank(message = "Payment method is required")
    @Pattern(regexp = "CARD|UPI|NET_BANKING")
    private String paymentMethod;
}