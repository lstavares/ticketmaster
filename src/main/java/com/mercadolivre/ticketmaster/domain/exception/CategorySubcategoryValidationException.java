package com.mercadolivre.ticketmaster.domain.exception;

public class CategorySubcategoryValidationException extends RuntimeException {
    public CategorySubcategoryValidationException(String message) {
        super(message);
    }
}
