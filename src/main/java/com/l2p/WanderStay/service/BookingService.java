package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.BookingRequest;
import com.l2p.WanderStay.dto.BookingResponse;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    BookingResponse create(BookingRequest request);

    BookingResponse getById(UUID id);

    List<BookingResponse> getByUser(UUID userId);

    void cancel(UUID id);
}