package com.l2p.WanderStay.mapper;

import com.l2p.WanderStay.dto.*;
import com.l2p.WanderStay.model.Hotel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelDTO toDTO(Hotel hotel);

    @Mapping(
            target = "roomCount",
            expression = "java(hotel.getRooms() != null ? hotel.getRooms().size() : 0)"
    )
    HotelSummaryDTO toSummaryDTO(Hotel hotel);

    List<HotelSummaryDTO> toSummaryDTOList(List<Hotel> hotels);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "rating", constant = "0.0")
    @Mapping(target = "active", constant = "true")
    Hotel toEntity(CreateHotelRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "rating", ignore = true)
    void updateHotel(UpdateHotelRequest request, @MappingTarget Hotel hotel);
}