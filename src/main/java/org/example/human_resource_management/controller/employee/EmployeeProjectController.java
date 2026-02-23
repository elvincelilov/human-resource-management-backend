package org.example.human_resource_management.controller.employee;

import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.projectDto.ProjectResponseDto;
import org.example.human_resource_management.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee/projects")
@RequiredArgsConstructor
public class EmployeeProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<ProjectResponseDto>> getMyProjects(Authentication authentication,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                                  @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(projectService.getMyProjects(authentication.getName(),pageable));
    }
}
