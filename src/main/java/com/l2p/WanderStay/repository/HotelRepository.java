package com.l2p.WanderStay.repository;

import com.l2p.WanderStay.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Page<Hotel> findByLocationContainingIgnoreCaseAndIsActiveTrue(String location, Pageable pageable);
    Page<Hotel> findByIsActiveTrue(Pageable pageable);
}