package com.springboot.employeepayrollapp.service;

import com.springboot.employeepayrollapp.model.Employee;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private Long nextId = 1L; // To simulate auto-increment ID

    // Get all employees
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employees;
    }

    // Add a new employee
    public Employee addEmployee(Employee employee) {
        employee.setId(nextId++); // Simulate DB ID assignment
        employees.add(employee);
        log.info("Added new employee: {}", employee);
        return employee;
    }

    // Update an existing employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        log.debug("Updating employee with id: {}", id);
        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();

        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            emp.setName(updatedEmployee.getName());
            emp.setDepartment(updatedEmployee.getDepartment());
            emp.setSalary(updatedEmployee.getSalary());
            log.info("Updated employee with id: {}", id);
            return emp;
        } else {
            log.error("Employee with id {} not found", id);
            throw new RuntimeException("Employee not found");
        }
    }

    // Delete an employee
    public void deleteEmployee(Long id) {
        log.warn("Deleting employee with id: {}", id);
        employees.removeIf(emp -> emp.getId().equals(id));
    }
}
