package com.l2p.WanderStay.service.impl;

import com.l2p.WanderStay.dto.PaymentRequest;
import com.l2p.WanderStay.dto.PaymentResponse;
import com.l2p.WanderStay.mapper.PaymentMapper;
import com.l2p.WanderStay.model.*;
import com.l2p.WanderStay.repository.*;
import com.l2p.WanderStay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse process(PaymentRequest request) {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // OPTIONAL: validate amount matches booking total
        if (request.getAmount().compareTo(booking.getTotalAmount()) != 0) {
            throw new RuntimeException("Payment amount mismatch");
        }

        Payment payment = paymentMapper.toEntity(request);
        payment.setBooking(booking);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId("TXN-" + UUID.randomUUID());

        return paymentMapper.toResponse(paymentRepository.save(payment));
    }
}