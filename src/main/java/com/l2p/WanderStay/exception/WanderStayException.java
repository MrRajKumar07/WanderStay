package com.l2p.WanderStay.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WanderStayException extends RuntimeException {
    private final HttpStatus status;

    public WanderStayException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}