package org.example.human_resource_management.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin - Users", description = "Admin operations related to user approval and management")
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @Operation(
            summary = "Approve user",
            description = "Approves a pending user registration. ADMIN only."
    )
    @ApiResponse(responseCode = "200", description = "User approved successfully")
    @PutMapping("/{userId}/approve")
    public ResponseEntity<Void> approveUser(
            @Parameter(description = "User ID", example = "3")
            @PathVariable Long userId) {

        userService.approveUser(userId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Reject user",
            description = "Rejects a pending user registration. ADMIN only."
    )
    @ApiResponse(responseCode = "200", description = "User rejected successfully")
    @PutMapping("/{userId}/reject")
    public ResponseEntity<Void> rejectUser(
            @Parameter(description = "User ID", example = "3")
            @PathVariable Long userId) {

        userService.rejectUser(userId);
        return ResponseEntity.ok().build();
    }
}

