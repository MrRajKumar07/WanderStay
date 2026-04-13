package com.l2p.WanderStay.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomResponse {

    private Long id;
    private Long hotelId;
    private String roomType;
    private BigDecimal pricePerNight;
    private int capacity;
    private int floorNumber;
    private boolean available;
    private String description;
    private String imageUrl;
}
