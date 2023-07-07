package com.intexsoft.salary_distribution_service.model.db;

import com.intexsoft.salary_distribution_service.annotation.ValidPeriod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "salary_distribution_service")
public class Budget {
    @Id
    @ValidPeriod
    private String period;
    @Column(nullable = false)
    @Positive
    private Double totalAmount;
}
