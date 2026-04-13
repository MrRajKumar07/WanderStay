package com.l2p.WanderStay.repository;

import com.l2p.WanderStay.entity.Hotel;
import com.l2p.WanderStay.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // 🔍 Search hotels by location (case-insensitive)
    Page<Hotel> findByLocationContainingIgnoreCaseAndIsActiveTrue(
            String location, Pageable pageable);

    // 📋 Get all active hotels (admin view)
    Page<Hotel> findByIsActiveTrue(Pageable pageable);

    // ⭐ Filter by rating
    Page<Hotel> findByRatingGreaterThanEqual(Double rating, Pageable pageable);

    // 📊 Count total hotels
    long count();
    Page<Hotel> findByLocationContainingIgnoreCaseAndIsActiveTrue(String location, Pageable pageable);
    Page<Hotel> findByIsActiveTrue(Pageable pageable);
}