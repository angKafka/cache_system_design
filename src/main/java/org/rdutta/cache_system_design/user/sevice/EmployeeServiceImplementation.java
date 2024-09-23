package org.rdutta.cache_system_design.user.sevice;

import jakarta.transaction.Transactional;
import org.rdutta.cache_system_design.cache.factory.CacheFactory;
import org.rdutta.cache_system_design.cache.main.PSCache;
import org.rdutta.cache_system_design.user.dao.EmployeeDAO;
import org.rdutta.cache_system_design.user.dto.EmployeeDTO;
import org.rdutta.cache_system_design.user.handlers.validations.EmailValidationCheck;
import org.rdutta.cache_system_design.user.handlers.validations.PhoneValidationCheck;
import org.rdutta.cache_system_design.user.mapper.Mapper;
import org.rdutta.cache_system_design.user.model.Employee;
import org.rdutta.cache_system_design.user.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImplementation implements EmployeeDAO {
    private final EmployeeRepository employeeRepository;
    private final Mapper mapper;
    private final PSCache<Integer, Employee> pscache;
    private final EmailValidationCheck emailValidationCheck;
    private final PhoneValidationCheck phoneValidationCheck;
    public EmployeeServiceImplementation(EmployeeRepository employeeRepository, Mapper mapper, PSCache<UUID, Employee> pscache, EmailValidationCheck emailValidationCheck, PhoneValidationCheck phoneValidationCheck) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
        this.pscache = new CacheFactory<Integer, Employee>().defaultCache(100);
        this.emailValidationCheck = emailValidationCheck;
        this.phoneValidationCheck = phoneValidationCheck;
    }
    @Transactional
    @Override
    public Integer addEmployee(EmployeeDTO dto) {
        Employee employee = null;
        Integer employeeId = null;
        try{
            employeeValidationChecks(dto);
            employee = mapper.toEmployee(dto);
            employeeId = employeeRepository.save(employee).getEmpId();

            if(employeeId != null) {
                if(pscache.get(employeeId) == null) {
                    pscache.put(employeeId, employee);
                    System.out.println("Employee cached successfully with ID: " + employeeId);
                }else{
                    System.out.println("Employee already exists in cache, not overwriting...");
                }
            } else {
                System.out.println("Failed to cache the employee. Invalid employee ID or employee object.");
            }
        }catch (Exception e){
            System.out.println("Error adding employee: " + e.getMessage());
        }
        return employeeId;
    }

    @Override
    public Employee getEmployee(Integer id) {
        Employee employee = pscache.get(id);
        try{
            if(employee == null) {
                Employee employee1 = employeeRepository.findById(id).orElse(null);

                if (employee1 != null) {
                    pscache.put(id, employee1);
                    System.out.println("Employee retrieved from DB and cached with ID: " + id);
                } else {
                    System.out.println("Employee not found in the database.");
                }
                return employee1;
            }
        }catch (Exception e){
            System.out.println("Error retrieving employee: " + e.getMessage());
        }
        System.out.println("Employee retrieved from Cache and cached with ID: " + id);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try{
        employees = employeeRepository.findAll();
        }catch (Exception e){
            System.out.println("Retrieved all employees from db.");
        }
        return employees;
    }

    @Transactional
    @Override
    public void updateEmployee(Integer id, EmployeeDTO dto) {
        try {
            Employee existingEmployee = employeeRepository.findById(id).orElse(null);
            if (existingEmployee == null) {
                System.out.println("Employee not found with ID: " + id);
                return;
            }

            existingEmployee.setEmail(dto.email());
            existingEmployee.setPhone(dto.phone());
            existingEmployee.setDepartment(dto.department());
            employeeRepository.save(existingEmployee);

            Employee cachedEmployee = pscache.get(id);
            if (cachedEmployee != null) {
                pscache.put(cachedEmployee.getEmpId(), existingEmployee);
                System.out.println("Employee updated successfully and cached with ID: " + id);
            } else {
                pscache.put(cachedEmployee.getEmpId(), existingEmployee);
                System.out.println("Employee not found in cache; added to cache with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteEmployee(Integer id) {
        try {
            if (pscache.get(id) != null) {
                pscache.remove(id);
                System.out.println("Employee removed from cache with ID: " + id);
            }

            employeeRepository.deleteById(id);
            System.out.println("Employee deleted from database with ID: " + id);
        } catch (Exception e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    private void employeeValidationChecks(EmployeeDTO dto) throws Exception {
        emailValidationCheck.setNext(phoneValidationCheck);
        phoneValidationCheck.handle(dto);
    }
}
