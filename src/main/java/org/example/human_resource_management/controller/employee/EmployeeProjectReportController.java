package org.example.human_resource_management.controller.employee;

import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.projectReportDto.ProjectReportCreateDto;
import org.example.human_resource_management.service.ProjectReportService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee/reports")
public class EmployeeProjectReportController {

    private final ProjectReportService projectReportService;

    @PostMapping
    public void createReport(Authentication authentication,
                             @RequestBody ProjectReportCreateDto dto) {
        projectReportService.createReport(authentication.getName(), dto);
    }
}
