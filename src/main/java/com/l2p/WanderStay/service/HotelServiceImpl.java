package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.*;
import com.l2p.WanderStay.exception.WanderStayException;
import com.l2p.WanderStay.mapper.HotelMapper;
import com.l2p.WanderStay.model.Hotel;
import com.l2p.WanderStay.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public Page<HotelSummaryDTO> searchHotels(String location, Pageable pageable) {
        return hotelRepository.findByLocationContainingIgnoreCaseAndIsActiveTrue(location, pageable).map(hotelMapper::toSummaryDTO);
    }
    @Override
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new WanderStayException("Hotel not found", HttpStatus.NOT_FOUND));
        return hotelMapper.toDTO(hotel);
    }
    @Override
    public HotelDTO createHotel(CreateHotelRequest request) {
        Hotel hotel = hotelMapper.toEntity(request);
        hotel.setActive(true);
        hotel.setRating(0.0);
        return hotelMapper.toDTO(hotelRepository.save(hotel));
    }
    @Override
    public HotelDTO updateHotel(Long id, UpdateHotelRequest request) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new WanderStayException("Hotel not found", HttpStatus.NOT_FOUND));
        if (request.getName() != null) hotel.setName(request.getName());
        if (request.getIsActive() != null) hotel.setActive(request.getIsActive());
        return hotelMapper.toDTO(hotelRepository.save(hotel));
    }
    @Override
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new WanderStayException("Hotel not found", HttpStatus.NOT_FOUND));
        hotel.setActive(false);
        hotelRepository.save(hotel);
    }
}