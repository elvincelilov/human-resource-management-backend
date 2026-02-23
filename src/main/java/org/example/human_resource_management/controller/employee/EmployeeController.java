package org.example.human_resource_management.controller.employee;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.employeeDto.EmployeeResponseDto;
import org.example.human_resource_management.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@Data
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/profile")
    public ResponseEntity<EmployeeResponseDto> getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(employeeService.getMyProfile(email));
    }

}
