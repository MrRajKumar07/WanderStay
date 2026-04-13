package com.l2p.WanderStay.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hotels")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Hotel name is required")
    @Size(max = 200, message = "Name cannot exceed 200 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Location cannot exceed 255 characters")
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String amenities;

    @Builder.Default
    @DecimalMin(value = "0.0", message = "Rating must be >= 0")
    @DecimalMax(value = "5.0", message = "Rating must be <= 5")
    private Double rating = 0.0;

    @Builder.Default
    private boolean isActive = true;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;

    @CreationTimestamp
    private LocalDateTime createdAt;
}