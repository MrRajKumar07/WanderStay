package com.l2p.WanderStay.mapper;

import com.l2p.WanderStay.dto.RoomRequest;
import com.l2p.WanderStay.dto.RoomResponse;
import com.l2p.WanderStay.model.Room;
import com.l2p.WanderStay.model.RoomType;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "hotel.id", source = "hotelId")
    @Mapping(target = "roomType", expression = "java(RoomType.valueOf(request.getRoomType()))")
    Room toEntity(RoomRequest request);

    @Mapping(target = "hotelId", source = "hotel.id")
    @Mapping(target = "roomType", expression = "java(room.getRoomType().name())")
    RoomResponse toResponse(Room room);
}
