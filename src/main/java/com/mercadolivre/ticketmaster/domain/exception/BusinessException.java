package com.mercadolivre.ticketmaster.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends IllegalArgumentException {

    private HttpStatus httpStatus;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, HttpStatus httpStatus) {
        this(message);
        this.httpStatus = httpStatus;
    }

}
