package com.l2p.WanderStay.mapper;

import com.l2p.WanderStay.dto.RoomRequest;
import com.l2p.WanderStay.dto.RoomResponse;
import com.l2p.WanderStay.model.Room;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "hotel.id", source = "hotelId")
    Room toEntity(RoomRequest request);

    @Mapping(target = "hotelId", source = "hotel.id")
    RoomResponse toResponse(Room room);
}