package org.example.human_resource_management.dto.leaveRequestDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestCreateDto {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
}
