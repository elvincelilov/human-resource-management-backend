package org.example.human_resource_management.dto.user;

import lombok.Data;
import org.example.human_resource_management.security.Role;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private Role role;
}
