package org.rdutta.cache_system_design.user.dao;

import org.rdutta.cache_system_design.user.dto.EmployeeDTO;
import org.rdutta.cache_system_design.user.model.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeDAO {
    Integer addEmployee(EmployeeDTO dto);
    Employee getEmployee(Integer id);
    List<Employee> getAllEmployees();
    void updateEmployee(Integer id, EmployeeDTO dto);
    void deleteEmployee(Integer id);
}
