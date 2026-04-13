package com.l2p.WanderStay.controller;

import com.l2p.WanderStay.dto.PaymentRequest;
import com.l2p.WanderStay.dto.PaymentResponse;
import com.l2p.WanderStay.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse process(@Valid @RequestBody PaymentRequest request) {
        return paymentService.process(request);
    }
}