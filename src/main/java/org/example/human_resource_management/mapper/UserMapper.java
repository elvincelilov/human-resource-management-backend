package org.example.human_resource_management.mapper;

import org.example.human_resource_management.dto.user.RegisterRequestDto;
import org.example.human_resource_management.dto.user.UserResponseDto;
import org.example.human_resource_management.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toEntity (RegisterRequestDto dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDto userResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRole(user.getRole());
        return userResponseDto;
    }
}
