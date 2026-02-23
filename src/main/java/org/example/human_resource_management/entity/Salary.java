package org.example.human_resource_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name="salaries")
@NoArgsConstructor
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String currency ;

    private LocalDate startDate;

    @OneToOne
    @JoinColumn(name="user_id",nullable=false,unique = true)
    private Employee employee;
}
