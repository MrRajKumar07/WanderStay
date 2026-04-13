package com.l2p.WanderStay.dto;

import lombok.Data;
import javax.validation.constraints.Size;

@Data
public class CancelBookingRequest {

    @Size(max = 500)
    private String reason;
}
