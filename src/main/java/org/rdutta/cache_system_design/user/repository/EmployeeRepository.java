package org.rdutta.cache_system_design.user.repository;

import org.rdutta.cache_system_design.user.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
