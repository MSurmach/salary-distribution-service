package com.intexsoft.salary_distribution_service.model.db;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(schema = "salary_distribution_service")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "mitarbeiterId")
public class Employee {
    @Id
    @JsonProperty("mitarbeiterId")
    @Positive
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team.id", nullable = false)
    @JsonIgnore
    private Team team;
}
