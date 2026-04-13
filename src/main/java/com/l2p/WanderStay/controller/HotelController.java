package com.l2p.WanderStay.controller;

import com.hotelbooking.dto.*;
import com.hotelbooking.service.HotelService;
import com.l2p.WanderStay.dto.HotelDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;

    @GetMapping
    public ResponseEntity<Page<HotelDTO>> search(
            @RequestParam String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.search(location, pageable));
    }

    @PostMapping
    public ResponseEntity<HotelDTO> create(
            @Valid @RequestBody CreateHotelRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateHotelRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }
}