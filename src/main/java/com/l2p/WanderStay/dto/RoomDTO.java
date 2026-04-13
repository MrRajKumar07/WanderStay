package com.l2p.WanderStay.dto;


import lombok.*;
import java.math.BigDecimal;

import com.l2p.WanderStay.model.RoomType;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RoomDTO {
    private Long id;
    private Long hotelId;
    private RoomType roomType;
    private BigDecimal pricePerNight;
    private Integer capacity;
    private boolean available;
    private String imageUrl;
}