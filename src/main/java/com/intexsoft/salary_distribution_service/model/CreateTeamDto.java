package com.intexsoft.salary_distribution_service.model;

import com.intexsoft.salary_distribution_service.model.db.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateTeamDto {
    @NotBlank
    private String codename;
    @NotEmpty
    private List<Employee> members;
}
