package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.RoomRequest;
import com.l2p.WanderStay.dto.RoomResponse;

import java.util.List;

public interface RoomService {

    RoomResponse create(RoomRequest request);

    RoomResponse getById(Long id);

    List<RoomResponse> getAll();

    List<RoomResponse> getByHotel(Long hotelId);

    void delete(Long id);
}