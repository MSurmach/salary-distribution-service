package com.intexsoft.salary_distribution_service.repository;

import com.intexsoft.salary_distribution_service.model.db.DailyWork;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyWorkRepository extends JpaRepository<DailyWork, Long> {
    @Query("SELECT SUM(dailywork.workDuration) FROM DailyWork dailywork" +
            " WHERE dailywork.beginDateAndTime >= :startPeriodDateTime AND dailywork.endDateAndTime <= :endPeriodDateTime")
    Double sumTotalWorkDurationByPeriod(@Param("startPeriodDateTime") LocalDateTime startPeriodDateTime,
                                        @Param("endPeriodDateTime") LocalDateTime endPeriodDateTime);

    @Query("SELECT dailywork.employeeId, SUM(dailywork.workDuration) FROM DailyWork dailywork" +
            " WHERE dailywork.beginDateAndTime >= :startPeriodDateTime AND dailywork.endDateAndTime <= :endPeriodDateTime" +
            " AND dailywork.employeeId IN :employeeIdList GROUP BY dailywork.employeeId")
    List<Tuple> findTotalEmployeeWorkDurationByPeriodAndEmployeeId(@Param("startPeriodDateTime") LocalDateTime startPeriodDateTime,
                                                                   @Param("endPeriodDateTime") LocalDateTime endPeriodDateTime,
                                                                   @Param("employeeIdList") List<Long> employeeIds);
}
