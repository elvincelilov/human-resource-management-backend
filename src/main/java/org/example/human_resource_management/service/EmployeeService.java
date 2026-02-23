package org.example.human_resource_management.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.employeeDto.EmployeeResponseDto;
import org.example.human_resource_management.entity.Employee;
import org.example.human_resource_management.mapper.EmployeeMapper;
import org.example.human_resource_management.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeResponseDto getMyProfile(String email) {
        Employee employee = employeeRepository.findByUserEmail(email)
                .orElseThrow(()-> new RuntimeException("Employee not found"));
        return EmployeeMapper.toResponse(employee);
    }

    public Page<EmployeeResponseDto> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(EmployeeMapper::toResponse);
    }
}
