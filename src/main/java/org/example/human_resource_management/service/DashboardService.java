package org.example.human_resource_management.service;

import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.adminDashboardDto.AdminDashboardStatsDto;
import org.example.human_resource_management.repository.EmployeeRepository;
import org.example.human_resource_management.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final EmployeeRepository employeeRepository;

    private final ProjectRepository projectRepository;

    public AdminDashboardStatsDto getAdminStats() {

        AdminDashboardStatsDto dto = new AdminDashboardStatsDto();
        dto.setTotalEmployees(employeeRepository.count());
        dto.setTotalProjects(projectRepository.count());

        return dto;
    }
}
