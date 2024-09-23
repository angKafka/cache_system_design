package org.rdutta.cache_system_design.user.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rdutta.cache_system_design.user.dao.EmployeeDAO;
import org.rdutta.cache_system_design.user.dto.EmployeeDTO;
import org.rdutta.cache_system_design.user.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeControllers {
    private final EmployeeDAO employeeService;

    @PostMapping("/create")
    public ResponseEntity<Integer> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Integer employeeId = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(employeeId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Integer id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}