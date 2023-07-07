package com.intexsoft.salary_distribution_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EmployeeDto {
    @JsonProperty("mitarbeiterId")
    private Long id;
    private BigDecimal salary;
}
