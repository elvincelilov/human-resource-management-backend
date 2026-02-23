package org.example.human_resource_management.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.leaveRequestDto.LeaveRequestResponseDto;
import org.example.human_resource_management.entity.Status;
import org.example.human_resource_management.service.LeaveRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Admin - Leave Requests", description = "Admin operations related to employee leave requests")
@RestController
@RequestMapping("/api/admin/leave-requests")
@RequiredArgsConstructor
public class AdminLeaveController {

    private final LeaveRequestService leaveRequestService;

    @Operation(
            summary = "Get all leave requests",
            description = "Returns paginated list of all leave requests. Accessible only by ADMIN role."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Leave requests fetched successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping
    public ResponseEntity<Page<LeaveRequestResponseDto>> getAllLeaveRequests(
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
        return ResponseEntity.ok(leaveRequestService.getAllLeaves(pageable));
    }

    @Operation(
            summary = "Update leave request status",
            description = "Updates the status of a specific leave request (APPROVED, REJECTED, etc.). ADMIN only."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Leave status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status value"),
            @ApiResponse(responseCode = "404", description = "Leave request not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStatus(
            @Parameter(description = "Leave request ID", example = "1")
            @PathVariable Long id,

            @Parameter(description = "New leave status", example = "APPROVED")
            @RequestParam Status status) {

        leaveRequestService.updateLeaveStatus(id, status);
        return ResponseEntity.ok().build();
    }
}
