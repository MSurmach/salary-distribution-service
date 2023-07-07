package com.intexsoft.salary_distribution_service.annotation;

import com.intexsoft.salary_distribution_service.validator.PeriodValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PeriodValidator.class)
public @interface ValidPeriod {
    String message() default "Constraint violation. Period must be specified in yyyy-MM format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
