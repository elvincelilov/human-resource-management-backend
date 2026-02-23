package org.example.human_resource_management.mapper;

import org.example.human_resource_management.dto.employeeDto.EmployeeResponseDto;
import org.example.human_resource_management.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public static EmployeeResponseDto toResponse(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(employee.getId());
        dto.setFullName(employee.getFullName());
        dto.setBirthday(employee.getBirthday());
        dto.setGender(employee.getGender());
        dto.setMobileNumber(employee.getMobileNumber());
        dto.setAddress(employee.getAddress());
        dto.setProfilePhoto(employee.getProfilePhoto());
        return dto;
    }

}
