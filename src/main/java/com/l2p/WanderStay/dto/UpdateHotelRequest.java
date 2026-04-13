package com.l2p.WanderStay.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateHotelRequest {

    @Size(min = 3, max = 200, message = "Name must be 3-200 characters")
    private String name;

    @Size(min = 2, max = 255, message = "Location must be 2-255 characters")
    private String location;

    @Size(max = 1000, message = "Description too long")
    private String description;

    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Image URL must be valid"
    )
    private String imageUrl;

    @Size(max = 500, message = "Amenities too long")
    private String amenities;

import lombok.*;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class UpdateHotelRequest {
    private String name;
    private String location;
    private String description;
    private String imageUrl;
    private String amenities;
    private Boolean isActive;
}