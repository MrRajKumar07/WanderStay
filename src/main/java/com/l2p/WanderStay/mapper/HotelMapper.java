package com.l2p.WanderStay.mapper;

import com.l2p.WanderStay.dto.CreateHotelRequest;
import com.l2p.WanderStay.dto.HotelDTO;
import com.l2p.WanderStay.dto.HotelSummaryDTO;
import com.l2p.WanderStay.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelMapper {
	
    HotelDTO toDTO(Hotel hotel);
    
    @Mapping(target = "roomCount", expression = "java(hotel.getRooms() != null ? hotel.getRooms().size() : 0)")
    HotelSummaryDTO toSummaryDTO(Hotel hotel);
    Hotel toEntity(CreateHotelRequest request);
}