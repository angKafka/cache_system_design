package org.rdutta.cache_system_design.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID", unique = true, nullable = false)
    private int empId;
    @Column(name = "FIRSTNAME",nullable = false, length = 50)
    private String firstName;
    @Column(name = "LASTNAME",nullable = false, length = 50)
    private String lastName;
    @Column(name = "EMAIL",unique = true, nullable = false, length = 150)
    private String email;
    @Column(name = "PHONE",unique = true, nullable = false, length = 11)
    private String phone;
    @Column(name = "DEPARTMENT",nullable = false, length = 6)
    private String department;
}
