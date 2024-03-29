package com.andersenlab.hoovercontrol.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = RequestValidator.class)
@Documented
public @interface RequestValid {
    String message() default "Incorrect request body";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
