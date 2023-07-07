package com.intexsoft.salary_distribution_service.controller;

import com.intexsoft.salary_distribution_service.annotation.ValidPeriod;
import com.intexsoft.salary_distribution_service.model.SimpleExceptionResponse;
import com.intexsoft.salary_distribution_service.model.TeamSalaryDto;
import com.intexsoft.salary_distribution_service.model.db.DailyWork;
import com.intexsoft.salary_distribution_service.service.SalaryDistributionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("period/{period}")
@Validated
@Slf4j
@Tag(name = "SalaryDistribution", description = "Represents the salary distribution API")
public class SalaryDistributionController {
    private final SalaryDistributionService salaryDistributionService;

    @Operation(
            summary = "Saves dailyWork data provided in list",
            responses = {
                    @ApiResponse(
                            responseCode = "201", description = "The saved dailyWork list",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = DailyWork.class)))),
                    @ApiResponse(
                            responseCode = "404", description = "Not found budget for period",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = SimpleExceptionResponse.class)))),
                    @ApiResponse(
                            responseCode = "400", description = "Bad request. Violating the constraints",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = SimpleExceptionResponse.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SimpleExceptionResponse.class)))
            })
    @PostMapping
    @ResponseStatus(CREATED)
    public List<DailyWork> saveEmployeeDailyWorkData(@PathVariable @ValidPeriod String period,
                                                     @RequestBody List<DailyWork> dailyWorkList) {
        log.info("IN saveEmployeeDailyWorkData: period = {}, dailyWorkList count: = {}", period, dailyWorkList.size());
        var dailyWorks = salaryDistributionService.saveAllDailyWork(dailyWorkList);
        log.info("OUT saveEmployeeDailyWorkData: number of saved dailyworks = {}", dailyWorks.size());
        return dailyWorks;
    }

    @Operation(
            summary = "Calculate employee's salary distribution in the specific team",
            parameters = {
                    @Parameter(
                            description = "The period needed for budget's fetching",
                            name = "period",
                            in = QUERY
                    ),
                    @Parameter(
                            description = "The team's id. The salary distribution will be calculated only for this team",
                            name = "id",
                            in = QUERY
                    ),
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Salary distribution calculation was processed succesfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TeamSalaryDto.class))),
                    @ApiResponse(
                            responseCode = "404", description = "No team was found," +
                            " or budget for the period isn't assigned",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = SimpleExceptionResponse.class)))),
                    @ApiResponse(
                            responseCode = "400", description = "Bad request. Violating the constraints",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = SimpleExceptionResponse.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SimpleExceptionResponse.class)))
            })
    @GetMapping("team/{id}")
    @ResponseStatus(OK)
    public TeamSalaryDto calculateTeamSalaryByTeamIdAndPeriod(@PathVariable @ValidPeriod String period,
                                                              @PathVariable("id") @Positive Long teamId) {
        log.info("IN calculateTeamSalaryByTeamIdAndPeriod: period = {}, teamId: = {}", period, teamId);
        var teamSalaryDto = salaryDistributionService.calculateTeamSalaryDistributionByPeriodAndTeamId(period, teamId);
        log.info("OUT calculateTeamSalaryByTeamIdAndPeriod: teamSalaryDto = {}", teamSalaryDto);
        return teamSalaryDto;
    }

    @Operation(
            summary = "Calculate employee's salary distribution in the specific team by their codenames, " +
                    "or for all teams if codename isn't provided",
            parameters = {
                    @Parameter(
                            description = "The period needed for budget's fetching",
                            name = "period",
                            in = QUERY
                    ),
                    @Parameter(
                            description = "The team's codename." +
                                    " The salary distribution will be calculated only for this team (in case if only one team will be found)," +
                                    " or for multiple teams",
                            name = "codename",
                            in = QUERY
                    ),
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Salary distribution calculation was processed succesfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = TeamSalaryDto.class)))),
                    @ApiResponse(
                            responseCode = "404", description = "No team was found," +
                            " or budget for the period isn't assigned",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = SimpleExceptionResponse.class)))),
                    @ApiResponse(
                            responseCode = "400", description = "Bad request. Violating the constraints",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = SimpleExceptionResponse.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SimpleExceptionResponse.class)))
            })
    @GetMapping("team")
    @ResponseStatus(OK)
    public List<TeamSalaryDto> calculateTeamSalaryByTeamCodenameAndPeriod(
            @PathVariable @ValidPeriod String period,
            @RequestParam(required = false, defaultValue = "") String codename) {
        log.info("IN calculateTeamSalaryByTeamCodenameAndPeriod: period = {}, codename: = {}", period, codename);
        var teamSalaryDtos = salaryDistributionService.calculateTeamSalaryDistributionByPeriodAndTeamCodename(period, codename);
        log.info("OUT calculateTeamSalaryByTeamCodenameAndPeriod: count of teamSalaryDtos = {}, content of teamSalaryDtos: {}",
                teamSalaryDtos.size(), teamSalaryDtos);
        return teamSalaryDtos;
    }
}
