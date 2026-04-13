package com.l2p.WanderStay.controller;

import com.l2p.WanderStay.dto.RoomRequest;
import com.l2p.WanderStay.dto.RoomResponse;
import com.l2p.WanderStay.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody RoomRequest request) {
        return ResponseEntity.ok(roomService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponse>> getByHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.getByHotel(hotelId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}