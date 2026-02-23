package org.example.human_resource_management.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.projectReportDto.ProjectReportCreateDto;
import org.example.human_resource_management.dto.projectReportDto.ProjectReportResponseDto;
import org.example.human_resource_management.entity.Employee;
import org.example.human_resource_management.entity.Project;
import org.example.human_resource_management.entity.ProjectReport;
import org.example.human_resource_management.mapper.ProjectReportMapper;
import org.example.human_resource_management.repository.EmployeeRepository;
import org.example.human_resource_management.repository.ProjectReportRepository;
import org.example.human_resource_management.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectReportService {
    private final ProjectReportRepository reportRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public void createReport(String email, ProjectReportCreateDto dto) {
        Employee employee = employeeRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getEmployees().contains(employee)) {
            throw new RuntimeException("Employee not assigned to this project");
        }

        ProjectReport report = new ProjectReport();
        report.setEmployee(employee);
        report.setProject(project);
        report.setDescription(dto.getDescription());
        report.setCreatedAt(LocalDateTime.now());
        reportRepository.save(report);
    }

    public Page<ProjectReportResponseDto> getAllReports(Pageable pageable) {
        return  reportRepository.findAll(pageable)
                .map(ProjectReportMapper::toResponseDto);
    }
}
