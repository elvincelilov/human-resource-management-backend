package org.example.human_resource_management.mapper;

import org.example.human_resource_management.dto.leaveRequestDto.LeaveRequestResponseDto;
import org.example.human_resource_management.entity.LeaveRequest;
import org.springframework.stereotype.Component;

@Component
public class LeaveRequestMapper {
    public static LeaveRequestResponseDto toResponseDto(LeaveRequest leave) {

        LeaveRequestResponseDto dto = new LeaveRequestResponseDto();
        dto.setId(leave.getId());
        dto.setEmployeeId(leave.getEmployee().getId());
        dto.setEmployeeName(leave.getEmployee().getFullName());
        dto.setFromDate(leave.getFromDate());
        dto.setToDate(leave.getToDate());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus().name());
        dto.setCreatedAt(leave.getCreatedAt());

        return dto;
    }
}
