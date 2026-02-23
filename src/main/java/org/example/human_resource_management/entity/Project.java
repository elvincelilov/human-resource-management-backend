package org.example.human_resource_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="projects")
@Data
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;


    private String projectName;
    private String clientName;
    private String projectType;
    private String projectManager;
    private String developingPlatform;
    private String databaseTechnology;

    @Column(length = 2000)
    private String projectDescription;

    @ManyToMany
    @JoinTable(
            name = "employee_projects",
            joinColumns = @JoinColumn (name="project_id"),
            inverseJoinColumns = @JoinColumn(name="employee_id")
    )
    private Set<Employee> employees = new HashSet<>();
}
