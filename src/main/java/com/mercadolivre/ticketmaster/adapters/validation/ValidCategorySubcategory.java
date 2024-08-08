package com.mercadolivre.ticketmaster.adapters.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CategorySubcategoryValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategorySubcategory {
    String message() default "Subcategory does not belong to the specified category";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

