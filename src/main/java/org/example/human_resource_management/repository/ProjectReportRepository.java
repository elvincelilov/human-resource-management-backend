package org.example.human_resource_management.repository;

import org.example.human_resource_management.entity.ProjectReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectReportRepository extends JpaRepository<ProjectReport, Long> {
    List<ProjectReport> findByEmployeeUserEmail(String email);
}
