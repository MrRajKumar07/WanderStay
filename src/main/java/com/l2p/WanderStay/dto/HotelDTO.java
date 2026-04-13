package com.l2p.WanderStay.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {

    private Long id;
    private String name;
    private String location;
    private String description;
    private String imageUrl;
    private String amenities;
    private Double rating;
    private boolean isActive;
}