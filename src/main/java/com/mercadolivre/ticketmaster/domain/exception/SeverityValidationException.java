package com.mercadolivre.ticketmaster.domain.exception;

public class SeverityValidationException extends RuntimeException {
    public SeverityValidationException(String message) {
        super(message);
    }
}
