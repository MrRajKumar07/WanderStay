package com.l2p.WanderStay.mapper;

import com.l2p.WanderStay.dto.PaymentRequest;
import com.l2p.WanderStay.dto.PaymentResponse;
import com.l2p.WanderStay.model.Payment;
import com.l2p.WanderStay.model.PaymentMethod;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "booking.id", source = "bookingId")
    @Mapping(target = "method", expression = "java(PaymentMethod.valueOf(request.getMethod()))")
    Payment toEntity(PaymentRequest request);

    @Mapping(target = "bookingId", source = "booking.id")
    @Mapping(target = "method", expression = "java(payment.getMethod().name())")
    @Mapping(target = "status", expression = "java(payment.getStatus().name())")
    PaymentResponse toResponse(Payment payment);
}