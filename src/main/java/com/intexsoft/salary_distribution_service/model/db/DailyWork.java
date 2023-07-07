package com.intexsoft.salary_distribution_service.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(schema = "salary_distribution_service")
@Data
public class DailyWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("mitarbeiterId")
    private Long employeeId;
    @JsonProperty("beginn")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S][.]")
    private LocalDateTime beginDateAndTime;
    @JsonProperty("ende")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S][.]")
    private LocalDateTime endDateAndTime;
    @JsonProperty("dauer")
    private Double workDuration;
}
