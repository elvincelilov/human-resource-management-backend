package org.example.human_resource_management.dto.projectReportDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectReportResponseDto {

    private Long reportId;
    private String projectName;
    private Long employeeId;
    private String employeeName;
    private String description;
    private LocalDateTime createdAt;
}
