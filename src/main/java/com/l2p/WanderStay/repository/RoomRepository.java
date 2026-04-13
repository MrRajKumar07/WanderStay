package com.l2p.WanderStay.repository;

import com.l2p.WanderStay.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotel_Id(Long hotelId);

    List<Room> findByAvailableTrue();

    List<Room> findByHotel_IdAndAvailableTrue(Long hotelId);
}