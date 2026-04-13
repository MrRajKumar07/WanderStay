package com.l2p.WanderStay.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateHotelRequest {
    private String name;
    private String location;
    private String description;
    private String imageUrl;
    private String amenities;
}