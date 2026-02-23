package org.example.human_resource_management.repository;

import org.example.human_resource_management.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserEmail(String email);
    long count();
    Page<Employee> findAll(Pageable pageable);
}
