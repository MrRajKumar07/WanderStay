package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {

    Page<HotelDTO> search(String location, Pageable pageable);

    HotelDTO getById(Long id);

    Page<HotelDTO> getAll(Pageable pageable);

    HotelDTO create(CreateHotelRequest request);

    HotelDTO update(Long id, UpdateHotelRequest request);

    void delete(Long id);
}