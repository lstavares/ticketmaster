package com.mercadolivre.ticketmaster.adapters.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SeverityValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSeverity {
    String message() default "Por favor, crie um ticket no link: http://example/fast, a equipe de guardian buscará resolver a sua issue.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}