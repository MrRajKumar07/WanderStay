package com.l2p.WanderStay.mapper;

import com.l2p.WanderStay.dto.BookingRequest;
import com.l2p.WanderStay.dto.BookingResponse;
import com.l2p.WanderStay.model.Booking;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "room.id", source = "roomId")
    Booking toEntity(BookingRequest request);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "status", expression = "java(booking.getStatus().name())")
    BookingResponse toResponse(Booking booking);
}
