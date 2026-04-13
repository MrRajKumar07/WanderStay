package com.l2p.WanderStay.controller;

import com.l2p.WanderStay.dto.BookingRequest;
import com.l2p.WanderStay.dto.BookingResponse;
import com.l2p.WanderStay.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponse create(@Valid @RequestBody BookingRequest request) {
        return bookingService.create(request);
    }

    @GetMapping("/{id}")
    public BookingResponse getById(@PathVariable UUID id) {
        return bookingService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<BookingResponse> getByUser(@PathVariable UUID userId) {
        return bookingService.getByUser(userId);
    }

    @PutMapping("/{id}/cancel")
    public void cancel(@PathVariable UUID id) {
        bookingService.cancel(id);
    }
}