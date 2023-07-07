package com.intexsoft.salary_distribution_service.util;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

import static com.intexsoft.salary_distribution_service.util.Constants.FIRST_DAY;
import static com.intexsoft.salary_distribution_service.util.Constants.PERIOD_FORMATTER;

public class DateTimeTransformator {

    public static Pair<LocalDateTime, LocalDateTime> transformPeriodRepresentationToDateTimeRange(String period) {
        var yearMonth = YearMonth.parse(period, PERIOD_FORMATTER);
        var startPeriodDateTime = yearMonth.atDay(FIRST_DAY).atTime(LocalTime.MIN);
        var endPeriodDateTime = yearMonth.atEndOfMonth().atTime(LocalTime.MAX);
        return Pair.of(startPeriodDateTime, endPeriodDateTime);
    }
}
