package com.mercadolivre.ticketmaster.adapters.validation;

import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import com.mercadolivre.ticketmaster.domain.exception.SeverityValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SeverityValidator implements ConstraintValidator<ValidSeverity, TicketDTO> {

    @Override
    public boolean isValid(TicketDTO ticketDTO, ConstraintValidatorContext context) {
        if (ticketDTO.getSeverityLevel() != null && ticketDTO.getSeverityLevel() == 1) {
            throw new SeverityValidationException("Por favor, crie um ticket no link: http://example/fast, a equipe de guardian buscará resolver a sua issue.");
        }
        return true;
    }
}
