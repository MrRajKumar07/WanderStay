package com.l2p.WanderStay.service;

import com.l2p.WanderStay.dto.PaymentRequest;
import com.l2p.WanderStay.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse process(PaymentRequest request);
}
