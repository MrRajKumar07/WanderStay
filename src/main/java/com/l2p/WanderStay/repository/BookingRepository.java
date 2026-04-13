package com.l2p.WanderStay.repository;

import com.l2p.WanderStay.model.Booking;
import com.l2p.WanderStay.model.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    // 🔹 Existing (keep if needed)
    List<Booking> findByUser_Id(UUID userId);

    List<Booking> findByRoom_Id(Long roomId);

    // 🔥 1. PAGINATION (IMPORTANT)
    Page<Booking> findByUser_Id(UUID userId, Pageable pageable);

    // 🔥 2. OWNERSHIP CHECK (IMPORTANT)
    Optional<Booking> findByIdAndUser_Id(UUID id, UUID userId);

    // 🔥 3. ACTIVE BOOKINGS FOR ROOM
    List<Booking> findByRoom_IdAndStatusNot(Long roomId, BookingStatus status);

    // 🔥 4. DOUBLE BOOKING PREVENTION (CRITICAL)
    @Query("""
        SELECT COUNT(b) > 0 FROM Booking b
        WHERE b.room.id = :roomId
        AND b.status != 'CANCELLED'
        AND NOT (b.checkOutDate <= :checkIn OR b.checkInDate >= :checkOut)
    """)
    boolean existsOverlappingBooking(
            @Param("roomId") Long roomId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut
    );

    // 🔥 5. ADMIN STATS
    long countByStatus(BookingStatus status);

    // 🔥 6. AUTO-COMPLETE OLD BOOKINGS (SCHEDULER)
    List<Booking> findByStatusAndCheckOutDateBefore(
            BookingStatus status,
            LocalDate date
    );
}