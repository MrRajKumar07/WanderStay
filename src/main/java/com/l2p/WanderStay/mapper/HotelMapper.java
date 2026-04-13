package com.l2p.WanderStay.mapper;

import com.l2p.WanderStay.dto.*;
import com.l2p.WanderStay.model.Hotel;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface HotelMapper {

    // 🔹 Entity → DTO
    HotelDTO toDTO(Hotel hotel);

    // 🔹 Entity → Summary DTO
    @Mapping(
            target = "roomCount",
            expression = "java(hotel.getRooms() != null ? hotel.getRooms().size() : 0)"
    )
    HotelSummaryDTO toSummaryDTO(Hotel hotel);

    List<HotelSummaryDTO> toSummaryDTOList(List<Hotel> hotels);

    // 🔹 Create → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "rating", constant = "0.0")
    @Mapping(target = "isActive", constant = "true")
    Hotel toEntity(CreateHotelRequest request);

    // 🔹 Update existing entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "rating", ignore = true)
    void updateHotel(UpdateHotelRequest request, @MappingTarget Hotel hotel);
}