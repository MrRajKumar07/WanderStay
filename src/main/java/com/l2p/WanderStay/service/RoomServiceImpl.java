package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.RoomRequest;
import com.l2p.WanderStay.dto.RoomResponse;
import com.l2p.WanderStay.mapper.RoomMapper;
import com.l2p.WanderStay.model.Hotel;
import com.l2p.WanderStay.model.Room;
import com.l2p.WanderStay.repository.HotelRepository;
import com.l2p.WanderStay.repository.RoomRepository;
import com.l2p.WanderStay.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomResponse create(RoomRequest request) {

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        Room room = roomMapper.toEntity(request);
        room.setHotel(hotel);

        return roomMapper.toResponse(roomRepository.save(room));
    }

    @Override
    public RoomResponse getById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return roomMapper.toResponse(room);
    }

    @Override
    public List<RoomResponse> getAll() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> getByHotel(Long hotelId) {
        return roomRepository.findByHotel_Id(hotelId)
                .stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}