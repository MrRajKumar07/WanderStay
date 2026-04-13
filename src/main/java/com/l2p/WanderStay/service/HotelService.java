package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {
    Page<HotelSummaryDTO> searchHotels(String location, Pageable pageable);
    HotelDTO getHotelById(Long id);
    HotelDTO createHotel(CreateHotelRequest request);
    HotelDTO updateHotel(Long id, UpdateHotelRequest request);
    void deleteHotel(Long id);
}