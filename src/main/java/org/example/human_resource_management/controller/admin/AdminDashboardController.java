package org.example.human_resource_management.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.adminDashboardDto.AdminDashboardStatsDto;
import org.example.human_resource_management.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin - Dashboard", description = "Admin dashboard statistics and overview")
@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final DashboardService dashboardService;

    @Operation(
            summary = "Get admin dashboard statistics",
            description = "Returns aggregated statistics for admin dashboard. ADMIN only."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dashboard statistics fetched successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping
    public ResponseEntity<AdminDashboardStatsDto> getDashboardStats() {

        return ResponseEntity.ok(dashboardService.getAdminStats());
    }
}
