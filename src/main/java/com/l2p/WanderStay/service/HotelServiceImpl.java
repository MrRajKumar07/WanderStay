package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.*;
import com.l2p.WanderStay.entity.Hotel;
import com.l2p.WanderStay.exception.ResourceNotFoundException;
import com.l2p.WanderStay.mapper.HotelMapper;
import com.l2p.WanderStay.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository repository;
    private final HotelMapper mapper;

    @Override
    public Page<HotelDTO> search(String location, Pageable pageable) {

        if (location == null || location.trim().isEmpty()) {
            return repository.findByIsActiveTrue(pageable)
                    .map(mapper::toDTO);
        }

        return repository
                .findByLocationContainingIgnoreCaseAndIsActiveTrue(location, pageable)
                .map(mapper::toDTO);
    }

    @Override
    public HotelDTO getById(Long id) {

        Hotel hotel = repository.findById(id)
                .filter(Hotel::isActive)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id: " + id));

        return mapper.toDTO(hotel);
    }

    @Override
    @Transactional
    public HotelDTO create(CreateHotelRequest request) {

        Hotel hotel = mapper.toEntity(request);
        hotel.setRating(0.0);
        hotel.setActive(true);

        return mapper.toDTO(repository.save(hotel));
    }

    @Override
    @Transactional
    public HotelDTO update(Long id, UpdateHotelRequest request) {

        Hotel hotel = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id: " + id));

        if (!hotel.isActive()) {
            throw new IllegalStateException("Cannot update inactive hotel");
        }

        mapper.updateHotel(request, hotel);

        return mapper.toDTO(repository.save(hotel));
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Hotel hotel = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id: " + id));

        if (!hotel.isActive()) {
            throw new IllegalStateException("Hotel already inactive");
        }

        hotel.setActive(false);

        repository.save(hotel);
    }
}