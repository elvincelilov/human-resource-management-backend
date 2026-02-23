package org.example.human_resource_management.mapper;

import org.example.human_resource_management.dto.projectDto.ProjectResponseDto;
import org.example.human_resource_management.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public static ProjectResponseDto toResponseDto(Project project) {

        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setProjectName(project.getProjectName());
        dto.setClientName(project.getClientName());
        dto.setProjectType(project.getProjectType());
        dto.setProjectManager(project.getProjectManager());
        dto.setDevelopingPlatform(project.getDevelopingPlatform());
        dto.setDatabaseTechnology(project.getDatabaseTechnology());
        dto.setProjectDescription(project.getProjectDescription());

        return dto;
    }
}
