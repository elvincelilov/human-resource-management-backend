package org.example.human_resource_management.repository;

import org.example.human_resource_management.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
    Optional<Salary> findByEmployeeId(Long employeeId);
    Optional<Salary> findByEmployeeUserEmail(String email);
}
