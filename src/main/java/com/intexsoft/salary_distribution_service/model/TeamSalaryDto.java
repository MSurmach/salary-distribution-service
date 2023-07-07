package com.intexsoft.salary_distribution_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TeamSalaryDto {
    List<EmployeeDto> members;
    @JsonProperty("teamId")
    private Long id;
}
