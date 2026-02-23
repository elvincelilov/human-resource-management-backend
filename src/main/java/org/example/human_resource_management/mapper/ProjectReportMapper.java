package org.example.human_resource_management.mapper;

import org.example.human_resource_management.dto.projectReportDto.ProjectReportResponseDto;
import org.example.human_resource_management.entity.ProjectReport;
import org.springframework.stereotype.Component;

@Component
public class ProjectReportMapper {
    public static ProjectReportResponseDto toResponseDto(ProjectReport report) {

        ProjectReportResponseDto dto = new ProjectReportResponseDto();
        dto.setReportId(report.getId());
        dto.setProjectName(report.getProject().getProjectName());
        dto.setEmployeeId(report.getEmployee().getId());
        dto.setEmployeeName(report.getEmployee().getFullName());
        dto.setDescription(report.getDescription());
        dto.setCreatedAt(report.getCreatedAt());

        return dto;
    }
}
