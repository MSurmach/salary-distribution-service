package com.intexsoft.salary_distribution_service.service;

import com.intexsoft.salary_distribution_service.model.db.DailyWork;
import jakarta.persistence.Tuple;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyWorkService {

    List<DailyWork> saveAllDailyWork(List<DailyWork> dailyWorkList);

    Double sumTotallyWorkDurationByPeriod(LocalDateTime startPeriodDateTime, LocalDateTime endPeriodDateTime);

    List<Tuple> findTotalEmployeeWorkDurationByPeriodAndEmployeeId(
            LocalDateTime startPeriodDateTime, LocalDateTime endPeriodDateTime, List<Long> employeeIds);
}
