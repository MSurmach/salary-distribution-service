package com.intexsoft.salary_distribution_service.service;

import com.intexsoft.salary_distribution_service.model.EmployeeDto;
import com.intexsoft.salary_distribution_service.model.TeamSalaryDto;
import com.intexsoft.salary_distribution_service.model.db.DailyWork;

import java.util.List;

public interface SalaryDistributionService {
    List<DailyWork> saveAllDailyWork(List<DailyWork> dailyWorkList);

    TeamSalaryDto calculateTeamSalaryDistributionByPeriodAndTeamId(String period, Long teamId);

    List<TeamSalaryDto> calculateTeamSalaryDistributionByPeriodAndTeamCodename(String period, String codename);
}
