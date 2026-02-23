package org.example.human_resource_management.dto.projectDto;

import lombok.Data;

@Data
public class ProjectResponseDto {
    private Long id;
    private String projectName;
    private String clientName;
    private String projectType;
    private String projectManager;
    private String developingPlatform;
    private String databaseTechnology;
    private String projectDescription;
}
