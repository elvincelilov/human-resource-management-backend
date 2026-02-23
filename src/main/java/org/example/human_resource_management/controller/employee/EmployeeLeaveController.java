package org.example.human_resource_management.controller.employee;

import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.leaveRequestDto.LeaveRequestCreateDto;
import org.example.human_resource_management.service.LeaveRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee/leave-requests")
@RequiredArgsConstructor
public class EmployeeLeaveController {
    private final LeaveRequestService leaveRequestService;
    @PostMapping
    public void createLeave(Authentication authentication
            , @RequestBody LeaveRequestCreateDto dto) {
        leaveRequestService.createLeave(authentication.getName(), dto);
    }

}
