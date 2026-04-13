package com.l2p.WanderStay.repository;

import com.l2p.WanderStay.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    Payment findByBooking_Id(UUID bookingId);
}