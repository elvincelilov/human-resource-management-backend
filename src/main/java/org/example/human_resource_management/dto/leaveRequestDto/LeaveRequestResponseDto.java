package org.example.human_resource_management.dto.leaveRequestDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LeaveRequestResponseDto {

    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private String status;
    private LocalDateTime createdAt;
}
