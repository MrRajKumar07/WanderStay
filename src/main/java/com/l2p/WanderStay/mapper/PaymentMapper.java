package com.l2p.WanderStay.mapper;

import com.l2p.WanderStay.dto.PaymentRequest;
import com.l2p.WanderStay.dto.PaymentResponse;
import com.l2p.WanderStay.model.Payment;
import com.l2p.WanderStay.model.PaymentMethod;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {PaymentMethod.class})
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "booking.id", source = "bookingId")
    @Mapping(target = "method", expression = "java(request.getMethod() != null ? PaymentMethod.valueOf(request.getMethod()) : null)")
    Payment toEntity(PaymentRequest request);

    @Mapping(target = "bookingId", source = "booking.id")
    @Mapping(target = "method", expression = "java(payment.getMethod() != null ? payment.getMethod().name() : null)")
    @Mapping(target = "status", expression = "java(payment.getStatus() != null ? payment.getStatus().name() : null)")
    PaymentResponse toResponse(Payment payment);
}