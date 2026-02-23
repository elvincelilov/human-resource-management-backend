package org.example.human_resource_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.securityDto.UserLoginDto;
import org.example.human_resource_management.dto.securityDto.UserLoginResponseDto;
import org.example.human_resource_management.dto.user.RegisterRequestDto;
import org.example.human_resource_management.dto.user.UserResponseDto;
import org.example.human_resource_management.security.JwtUtil;
import org.example.human_resource_management.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody RegisterRequestDto dto) {

        UserResponseDto response = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(
            @Valid @RequestBody UserLoginDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
}
