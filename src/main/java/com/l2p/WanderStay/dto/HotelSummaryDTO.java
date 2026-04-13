package com.l2p.WanderStay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelSummaryDTO {
    private Long id;
    private String name;
    private String location;
    private Double rating;
    private String imageUrl;
    private int roomCount;
}