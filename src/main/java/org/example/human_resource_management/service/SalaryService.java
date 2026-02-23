package org.example.human_resource_management.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.salaryDto.SalaryCreateDto;
import org.example.human_resource_management.dto.salaryDto.SalaryResponseDto;
import org.example.human_resource_management.entity.Employee;
import org.example.human_resource_management.entity.Salary;
import org.example.human_resource_management.mapper.SalaryMapper;
import org.example.human_resource_management.repository.EmployeeRepository;
import org.example.human_resource_management.repository.SalaryRepository;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class SalaryService {
    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void assignSalary(Long employeeId, SalaryCreateDto dto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Salary salary = salaryRepository.findByEmployeeId(employeeId)
                .orElse(new Salary());


        salary.setAmount(dto.getAmount());
        salary.setCurrency(dto.getCurrency());
        salary.setStartDate(dto.getStartDate());
        salary.setEmployee(employee);

        salaryRepository.save(salary);
    }

    public SalaryResponseDto getMySalary(String email) {
        Salary salary = salaryRepository.findByEmployeeUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        return SalaryMapper.toResponseDto(salary);
    }
}
