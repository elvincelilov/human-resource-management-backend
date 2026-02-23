package org.example.human_resource_management.controller.employee;

import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.salaryDto.SalaryResponseDto;
import org.example.human_resource_management.service.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee/salary")
@RequiredArgsConstructor
public class EmployeeSalaryController {

    private final SalaryService salaryService;

    @GetMapping
    public ResponseEntity<SalaryResponseDto> getMySalary(Authentication authentication) {
        return ResponseEntity.ok(salaryService.getMySalary(authentication.getName()));
    }
}
