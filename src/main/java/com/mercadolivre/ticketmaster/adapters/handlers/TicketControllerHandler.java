package com.mercadolivre.ticketmaster.adapters.handlers;

import com.mercadolivre.ticketmaster.adapters.TicketController;
import com.mercadolivre.ticketmaster.domain.exception.BusinessException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.of;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.internalServerError;

@RestControllerAdvice(assignableTypes = {TicketController.class})
public class TicketControllerHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericExceptions(Exception e){
        return internalServerError().body(of("message", e.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e){
        return new ResponseEntity<>(of("message", e.getMessage()), e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return badRequest().body(errors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "subcategoryId";
        String errorMessage = ex.getCause().getMessage();
        errors.put(fieldName, errorMessage);
        return badRequest().body(errors);
    }

}
