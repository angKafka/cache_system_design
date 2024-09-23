package org.rdutta.cache_system_design.user.mapper;

import org.rdutta.cache_system_design.user.dto.EmployeeDTO;
import org.rdutta.cache_system_design.user.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public Employee toEmployee(EmployeeDTO dto) {
        return Employee.builder()
                .firstName(dto.firstname())
                .lastName(dto.lastname())
                .email(dto.email())
                .phone(dto.phone())
                .department(dto.department())
                .build();
    }

    public EmployeeDTO toEmployeeDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDepartment()
        );
    }
}
