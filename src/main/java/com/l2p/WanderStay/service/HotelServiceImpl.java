package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.*;
import com.l2p.WanderStay.entity.Hotel;
import com.l2p.WanderStay.mapper.HotelMapper;
import com.l2p.WanderStay.repository.HotelRepository;
import com.l2p.WanderStay.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository repository;
    private final HotelMapper mapper;

    // 🔍 Search hotels
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

    // 📄 Get by ID
    @Override
    public HotelDTO getById(Long id) {

        Hotel hotel = repository.findById(id)
                .filter(Hotel::isActive)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));

        return mapper.toDTO(hotel);
    }

    // 📋 Get all hotels
    @Override
    public Page<HotelDTO> getAll(Pageable pageable) {
        return repository.findByIsActiveTrue(pageable)
                .map(mapper::toDTO);
    }

    // ➕ Create hotel
    @Override
    public HotelDTO create(CreateHotelRequest request) {

        Hotel hotel = mapper.toEntity(request);

        if (hotel.getRating() == null) {
            hotel.setRating(0.0);
        }

        hotel.setActive(true);

        return mapper.toDTO(repository.save(hotel));
    }

    // ✏️ Update hotel
    @Override
    public HotelDTO update(Long id, UpdateHotelRequest request) {

        Hotel hotel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));

        if (!hotel.isActive()) {
            throw new RuntimeException("Cannot update inactive hotel");
        }

        if (request.getName() != null && !request.getName().isBlank()) {
            hotel.setName(request.getName());
        }

        if (request.getLocation() != null && !request.getLocation().isBlank()) {
            hotel.setLocation(request.getLocation());
        }

        if (request.getDescription() != null) {
            hotel.setDescription(request.getDescription());
        }

        if (request.getImageUrl() != null) {
            hotel.setImageUrl(request.getImageUrl());
        }

        if (request.getAmenities() != null) {
            hotel.setAmenities(request.getAmenities());
        }

        if (request.getIsActive() != null) {
            hotel.setActive(request.getIsActive());
        }

        return mapper.toDTO(repository.save(hotel));
    }

    // ❌ Soft delete
    @Override
    public void delete(Long id) {

        Hotel hotel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));

        if (!hotel.isActive()) {
            throw new RuntimeException("Hotel already inactive");
        }

        hotel.setActive(false);
        repository.save(hotel);
    }
}