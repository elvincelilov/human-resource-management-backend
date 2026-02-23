package org.example.human_resource_management.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.human_resource_management.entity.Gender;

import java.time.LocalDate;

@Data
public class RegisterRequestDto {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password can not be empty")
    @Size(min=6)
    private String password;

    @NotBlank
    private String fullName;

    private LocalDate birthday;

    private Gender gender;

    private String mobileNumber;

    private String address;

    private String profilePhoto;
}
