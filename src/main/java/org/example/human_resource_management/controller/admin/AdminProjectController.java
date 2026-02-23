package org.example.human_resource_management.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.projectDto.ProjectCreateDto;
import org.example.human_resource_management.dto.projectDto.ProjectResponseDto;
import org.example.human_resource_management.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Admin - Projects", description = "Admin operations related to project management")
@RestController
@RequestMapping("/api/admin/projects")
@RequiredArgsConstructor
public class AdminProjectController {

    private final ProjectService projectService;

    @Operation(
            summary = "Create a new project",
            description = "Creates a new project. Accessible only by ADMIN role."
    )
    @ApiResponse(responseCode = "200", description = "Project created successfully")
    @PostMapping
    public ResponseEntity<Long> createProject(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Project creation data")
            @RequestBody ProjectCreateDto dto) {

        return ResponseEntity.ok(projectService.createProject(dto));
    }

    @Operation(
            summary = "Assign employee to project",
            description = "Assigns an employee to a specific project. ADMIN only."
    )
    @ApiResponse(responseCode = "200", description = "Employee assigned successfully")
    @PostMapping("/{projectId}/employee/{employeeId}")
    public ResponseEntity<Void> assignEmployee(
            @Parameter(description = "Project ID", example = "1")
            @PathVariable Long projectId,

            @Parameter(description = "Employee ID", example = "5")
            @PathVariable Long employeeId) {

        projectService.assignProjectToEmployee(projectId, employeeId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Get all projects",
            description = "Returns paginated list of all projects."
    )
    @GetMapping
    public ResponseEntity<Page<ProjectResponseDto>> getAllProjects(
            @Parameter(description = "Page number", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Field to sort by", example = "id")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sort direction: asc or desc", example = "asc")
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(projectService.getAllProjects(pageable));
    }
}