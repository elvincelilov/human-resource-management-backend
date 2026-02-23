package org.example.human_resource_management.dto.securityDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
