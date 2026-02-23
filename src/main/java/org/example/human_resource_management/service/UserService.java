package org.example.human_resource_management.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.human_resource_management.dto.securityDto.UserLoginDto;
import org.example.human_resource_management.dto.securityDto.UserLoginResponseDto;
import org.example.human_resource_management.dto.user.RegisterRequestDto;
import org.example.human_resource_management.dto.user.UserResponseDto;
import org.example.human_resource_management.entity.Employee;
import org.example.human_resource_management.entity.Status;
import org.example.human_resource_management.entity.User;
import org.example.human_resource_management.mapper.UserMapper;
import org.example.human_resource_management.repository.EmployeeRepository;
import org.example.human_resource_management.repository.UserRepository;
import org.example.human_resource_management.security.JwtUtil;
import org.example.human_resource_management.security.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponseDto register (RegisterRequestDto userCreateDto) {
        if(userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw  new RuntimeException("Email already exists");
        }

        User user = UserMapper.toEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRole(Role.EMPLOYEE);
        user.setStatus(Status.PENDING);

        User savedUser = userRepository.save(user);

        Employee employee = new Employee();
        employee.setFullName(userCreateDto.getFullName());
        employee.setBirthday(userCreateDto.getBirthday());
        employee.setGender(userCreateDto.getGender());
        employee.setMobileNumber(userCreateDto.getMobileNumber());
        employee.setAddress(userCreateDto.getAddress());
        employee.setProfilePhoto(userCreateDto.getProfilePhoto());
        employee.setUser(savedUser);

        employeeRepository.save(employee);
        return UserMapper.userResponseDto(savedUser);
    }

    public UserLoginResponseDto login(UserLoginDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (user.getStatus() != Status.APPROVED) {
            throw new RuntimeException("Your account is not approved yet");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        String token = jwtUtil.generateToken(user.getEmail());

        UserLoginResponseDto response = new UserLoginResponseDto();
        response.setToken(token);
        return response;
    }

    @Transactional
    public void approveUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getStatus() == Status.APPROVED) {
            return;
        }

        user.setStatus(Status.APPROVED);
    }

    @Transactional
    public void rejectUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(Status.REJECTED);
    }

}
