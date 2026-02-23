package org.example.human_resource_management.repository;

import org.example.human_resource_management.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project>  findByEmployeesUserEmail(String email, Pageable pageable);
    long count();
}
