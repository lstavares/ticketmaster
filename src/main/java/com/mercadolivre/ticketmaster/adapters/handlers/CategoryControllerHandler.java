package com.mercadolivre.ticketmaster.adapters.handlers;

import com.mercadolivre.ticketmaster.adapters.CategoryController;
import com.mercadolivre.ticketmaster.domain.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.Map.of;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice(assignableTypes = {CategoryController.class})
public class CategoryControllerHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericExceptions(Exception e){
        return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e){
        return new ResponseEntity<>(of("message", e.getMessage()), e.getHttpStatus());
    }

}
