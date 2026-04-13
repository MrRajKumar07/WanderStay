package com.l2p.WanderStay.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomRequest {

    @NotNull(message = "Hotel ID is required")
    private Long hotelId;

    @NotBlank(message = "Room type is required")
    @Pattern(
            regexp = "SINGLE|DOUBLE|DELUXE|SUITE",
            message = "Room type must be SINGLE, DOUBLE, DELUXE, or SUITE"
    )
    private String roomType;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal pricePerNight;

    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 10, message = "Capacity cannot exceed 10")
    private int capacity;

    @Min(value = 0, message = "Floor number cannot be negative")
    private int floorNumber;

    private boolean available = true;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Size(max = 500, message = "Image URL cannot exceed 500 characters")
    private String imageUrl;
}