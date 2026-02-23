package org.example.human_resource_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String fullName ;

    private LocalDate birthday ;

    @Enumerated(EnumType.STRING)
    private Gender gender ;

    private String mobileNumber ;

    private String address;

    private String profilePhoto;

    @OneToOne
    @JoinColumn(name="user_id",nullable=false,unique=true)
    private User user ;


}
