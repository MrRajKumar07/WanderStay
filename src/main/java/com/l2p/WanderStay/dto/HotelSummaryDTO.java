package com.l2p.WanderStay.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class HotelSummaryDTO {
    private Long id;
    private String name;
    private String location;
    private String imageUrl;
    private Double rating;
    private Integer roomCount;
}