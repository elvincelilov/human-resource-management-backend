package org.example.human_resource_management.dto.employeeDto;

import lombok.Data;
import org.example.human_resource_management.entity.Gender;

import java.time.LocalDate;

@Data
public class EmployeeResponseDto {
    private Long id;
    private String fullName;
    private LocalDate birthday;
    private Gender gender;
    private String mobileNumber;
    private String address;
    private String profilePhoto;

}
