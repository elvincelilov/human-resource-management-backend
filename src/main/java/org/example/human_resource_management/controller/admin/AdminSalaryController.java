package org.example.human_resource_management.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.salaryDto.SalaryCreateDto;
import org.example.human_resource_management.service.SalaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admin - Salaries", description = "Admin operations related to employee salaries")
@RestController
@RequestMapping("/api/admin/salaries")
@RequiredArgsConstructor
public class AdminSalaryController {

    private final SalaryService salaryService;

    @Operation(
            summary = "Assign salary to employee",
            description = "Assigns a salary record to a specific employee. Accessible only by ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Salary assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "400", description = "Invalid salary data")
    })
    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<Void> assignSalary(
            @Parameter(description = "Employee ID", example = "5")
            @PathVariable Long employeeId,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Salary creation data"
            )
            @RequestBody SalaryCreateDto dto) {

        salaryService.assignSalary(employeeId, dto);
        return ResponseEntity.ok().build();
    }
}
