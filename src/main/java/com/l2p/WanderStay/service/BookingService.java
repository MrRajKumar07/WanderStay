package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookingService {

    BookingResponse create(BookingRequest request);

    BookingResponse getById(UUID id);

    Page<BookingSummaryDTO> getMyBookings(UUID userId, Pageable pageable);

    BookingResponse cancel(UUID id, String reason);
}