package com.intexsoft.salary_distribution_service.service.impl;

import com.intexsoft.salary_distribution_service.model.db.DailyWork;
import com.intexsoft.salary_distribution_service.repository.DailyWorkRepository;
import com.intexsoft.salary_distribution_service.service.DailyWorkService;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyWorkServiceImpl implements DailyWorkService {

    private final DailyWorkRepository dailyWorkRepository;

    @Override
    public List<DailyWork> saveAllDailyWork(List<DailyWork> dailyWorkList) {
        return dailyWorkRepository.saveAll(dailyWorkList);
    }

    @Override
    public Double sumTotallyWorkDurationByPeriod(LocalDateTime startPeriodDateTime, LocalDateTime endPeriodDateTime) {
        return dailyWorkRepository.sumTotalWorkDurationByPeriod(startPeriodDateTime, endPeriodDateTime);
    }

    @Override
    public List<Tuple> findTotalEmployeeWorkDurationByPeriodAndEmployeeId(
            LocalDateTime startPeriodDateTime, LocalDateTime endPeriodDateTime, List<Long> employeeIds) {
        return dailyWorkRepository.findTotalEmployeeWorkDurationByPeriodAndEmployeeId(
                startPeriodDateTime,
                endPeriodDateTime,
                employeeIds);
    }
}
