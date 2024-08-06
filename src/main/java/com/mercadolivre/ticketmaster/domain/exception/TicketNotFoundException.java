package com.mercadolivre.ticketmaster.domain.exception;

import static com.mercadolivre.ticketmaster.application.utils.Constants.Exceptions.TICKET_NOT_FOUND_MESSAGE;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class TicketNotFoundException extends BusinessException {

    public TicketNotFoundException(long ticketId) {
        super(format(TICKET_NOT_FOUND_MESSAGE, ticketId), NOT_FOUND);
    }

}
