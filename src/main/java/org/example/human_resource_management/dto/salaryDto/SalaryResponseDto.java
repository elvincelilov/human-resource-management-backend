package org.example.human_resource_management.dto.salaryDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SalaryResponseDto {
    private Double amount;
    private String currency;
    private LocalDate startDate;
}
