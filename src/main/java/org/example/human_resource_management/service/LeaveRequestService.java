package org.example.human_resource_management.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.leaveRequestDto.LeaveRequestCreateDto;
import org.example.human_resource_management.dto.leaveRequestDto.LeaveRequestResponseDto;
import org.example.human_resource_management.entity.Employee;
import org.example.human_resource_management.entity.LeaveRequest;
import org.example.human_resource_management.entity.Status;
import org.example.human_resource_management.mapper.LeaveRequestMapper;
import org.example.human_resource_management.repository.EmployeeRepository;
import org.example.human_resource_management.repository.LeaveRequestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void createLeave(String email, LeaveRequestCreateDto dto){
        Employee employee = employeeRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(employee);
        leave.setFromDate(dto.getFromDate());
        leave.setToDate(dto.getToDate());
        leave.setReason(dto.getReason());
        leave.setStatus(Status.PENDING);
        leave.setCreatedAt(LocalDateTime.now());

        leaveRequestRepository.save(leave);
    }

    public Page<LeaveRequestResponseDto> getAllLeaves (Pageable pageable){
        return leaveRequestRepository.findAll(pageable)
                .map(LeaveRequestMapper::toResponseDto);
    }

    public void updateLeaveStatus(Long leaveId, Status status){
        LeaveRequest leave = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(status);
        leaveRequestRepository.save(leave);
    }
}
