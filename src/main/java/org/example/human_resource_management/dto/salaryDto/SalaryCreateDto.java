package org.example.human_resource_management.dto.salaryDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SalaryCreateDto {
    private Double amount;
    private String currency;
    private LocalDate startDate;
}
