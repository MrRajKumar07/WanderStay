package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.BookingRequest;
import com.l2p.WanderStay.dto.BookingResponse;
import com.l2p.WanderStay.mapper.BookingMapper;
import com.l2p.WanderStay.model.*;
import com.l2p.WanderStay.repository.*;
import com.l2p.WanderStay.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BookingResponse create(BookingRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        long days = ChronoUnit.DAYS.between(
                request.getCheckInDate(),
                request.getCheckOutDate()
        );

        BigDecimal total = room.getPricePerNight()
                .multiply(BigDecimal.valueOf(days));

        Booking booking = bookingMapper.toEntity(request);
        booking.setUser(user);
        booking.setRoom(room);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setTotalAmount(total);
        booking.setConfirmationNumber("CONF-" + UUID.randomUUID());

        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    @Override
    public BookingResponse getById(UUID id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<BookingResponse> getByUser(UUID userId) {
        return bookingRepository.findByUser_Id(userId)
                .stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void cancel(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
}