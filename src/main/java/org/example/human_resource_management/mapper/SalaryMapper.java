package org.example.human_resource_management.mapper;

import org.example.human_resource_management.dto.salaryDto.SalaryResponseDto;
import org.example.human_resource_management.entity.Salary;
import org.springframework.stereotype.Component;

@Component
public class SalaryMapper {
    public static SalaryResponseDto toResponseDto(Salary salary) {
        SalaryResponseDto salaryResponseDto = new SalaryResponseDto();
        salaryResponseDto.setAmount(salary.getAmount());
        salaryResponseDto.setCurrency(salary.getCurrency());
        salaryResponseDto.setStartDate(salary.getStartDate());
        return salaryResponseDto;
    }
}
