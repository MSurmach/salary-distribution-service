package com.intexsoft.salary_distribution_service.validator;

import com.intexsoft.salary_distribution_service.annotation.ValidPeriod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Objects;

import static com.intexsoft.salary_distribution_service.util.Constants.PERIOD_FORMATTER;

public class PeriodValidator implements ConstraintValidator<ValidPeriod, String> {

    @Override
    public boolean isValid(String period, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(period)) return false;
        try {
            YearMonth.parse(period, PERIOD_FORMATTER);
            return true;
        } catch (DateTimeException exception) {
            return false;
        }
    }
}
