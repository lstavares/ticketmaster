package com.mercadolivre.ticketmaster.domain.exception;

import static com.mercadolivre.ticketmaster.application.utils.Constants.Exceptions.CATEGORY_NOT_FOUND_MESSAGE;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class CategoryNotFoundException extends BusinessException {

    public CategoryNotFoundException(long ticketId) {
        super(format(CATEGORY_NOT_FOUND_MESSAGE, ticketId), NOT_FOUND);
    }

}
