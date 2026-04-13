package com.l2p.WanderStay.controller;

import com.l2p.WanderStay.dto.*;
import com.l2p.WanderStay.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<Page<HotelSummaryDTO>> search(@RequestParam(defaultValue = "") String location, Pageable pageable) {
        return ResponseEntity.ok(hotelService.searchHotels(location, pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
    @PostMapping
    public ResponseEntity<HotelDTO> create(@RequestBody CreateHotelRequest request) {
        return ResponseEntity.ok(hotelService.createHotel(request));
    }
}