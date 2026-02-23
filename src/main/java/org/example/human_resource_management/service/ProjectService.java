package org.example.human_resource_management.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.projectDto.ProjectCreateDto;
import org.example.human_resource_management.dto.projectDto.ProjectResponseDto;
import org.example.human_resource_management.entity.Employee;
import org.example.human_resource_management.entity.Project;
import org.example.human_resource_management.mapper.ProjectMapper;
import org.example.human_resource_management.repository.EmployeeRepository;
import org.example.human_resource_management.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public Long createProject(ProjectCreateDto dto) {

        Project project = new Project();
        project.setProjectName(dto.getProjectName());
        project.setClientName(dto.getClientName());
        project.setProjectType(dto.getProjectType());
        project.setProjectManager(dto.getProjectManager());
        project.setDevelopingPlatform(dto.getDevelopingPlatform());
        project.setDatabaseTechnology(dto.getDatabaseTechnology());
        project.setProjectDescription(dto.getProjectDescription());

        return projectRepository.save(project).getId();
    }

    @Transactional
    public void assignProjectToEmployee(Long projectId, Long employeeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        project.getEmployees().add(employee);
    }

    public Page<ProjectResponseDto> getMyProjects(String email,Pageable pageable) {
        return projectRepository.findByEmployeesUserEmail(email,pageable)
                .map(ProjectMapper :: toResponseDto);
    }

    public Page<ProjectResponseDto> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(ProjectMapper::toResponseDto);
    }
}
