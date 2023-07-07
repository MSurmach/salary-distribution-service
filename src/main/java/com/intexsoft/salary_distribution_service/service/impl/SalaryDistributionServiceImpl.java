package com.intexsoft.salary_distribution_service.service.impl;

import com.intexsoft.salary_distribution_service.model.EmployeeDto;
import com.intexsoft.salary_distribution_service.model.TeamSalaryDto;
import com.intexsoft.salary_distribution_service.model.db.DailyWork;
import com.intexsoft.salary_distribution_service.model.db.Employee;
import com.intexsoft.salary_distribution_service.model.db.Team;
import com.intexsoft.salary_distribution_service.service.BudgetService;
import com.intexsoft.salary_distribution_service.service.DailyWorkService;
import com.intexsoft.salary_distribution_service.service.SalaryDistributionService;
import com.intexsoft.salary_distribution_service.service.TeamService;
import com.intexsoft.salary_distribution_service.util.DateTimeTransformator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaryDistributionServiceImpl implements SalaryDistributionService {

    private final DailyWorkService dailyWorkService;
    private final BudgetService budgetService;
    private final TeamService teamService;

    public List<DailyWork> saveAllDailyWork(List<DailyWork> dailyWorkList) {
        return dailyWorkService.saveAllDailyWork(dailyWorkList);
    }

    public TeamSalaryDto calculateTeamSalaryDistributionByPeriodAndTeamId(String period, Long teamId) {
        return TeamSalaryDto.builder()
                .id(teamId)
                .members(calculateTeamSalary(period, teamService.findTeamById(teamId)))
                .build();
    }

    @Override
    public List<TeamSalaryDto> calculateTeamSalaryDistributionByPeriodAndTeamCodename(String period, String codename) {
        var teams = teamService.findAllTeams(codename);
        return teams.stream().map(team ->
                        TeamSalaryDto.builder()
                                .id(team.getId())
                                .members(calculateTeamSalary(period, team))
                                .build())
                .collect(Collectors.toList());
    }

    private List<EmployeeDto> calculateTeamSalary(String period, Team team) {
        var totalAmount = budgetService.findBudgetByPeriod(period).getTotalAmount();
        var dateTimePeriodPair = DateTimeTransformator.transformPeriodRepresentationToDateTimeRange(period);
        var startPeriodDateTime = dateTimePeriodPair.getLeft();
        var endPeriodDateTime = dateTimePeriodPair.getRight();
        var totalWorkDuration = dailyWorkService.sumTotallyWorkDurationByPeriod(startPeriodDateTime, endPeriodDateTime);
        var employeeIds = team.getMembers().stream().map(Employee::getId).collect(Collectors.toList());
        return dailyWorkService.findTotalEmployeeWorkDurationByPeriodAndEmployeeId(
                        startPeriodDateTime,
                        endPeriodDateTime,
                        employeeIds).stream().
                map(tuple -> {
                    var employeeId = tuple.get(0, Long.class);
                    var totalEmployeeWorkDuration = tuple.get(1, Double.class);
                    var salary = totalEmployeeWorkDuration * totalAmount / totalWorkDuration;
                    return new EmployeeDto(employeeId, BigDecimal.valueOf(salary).setScale(2, RoundingMode.UP));
                }).
                collect(Collectors.toList());
    }
}
