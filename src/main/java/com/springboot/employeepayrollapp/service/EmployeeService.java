package com.springboot.employeepayrollapp.service;

import com.springboot.employeepayrollapp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private Long nextId = 1L; // To simulate auto-increment ID

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Add a new employee
    public Employee addEmployee(Employee employee) {
        employee.setId(nextId++); // Simulate DB ID assignment
        employees.add(employee);
        return employee;
    }

    // Update an existing employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();

        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            emp.setName(updatedEmployee.getName());
            emp.setDepartment(updatedEmployee.getDepartment());
            emp.setSalary(updatedEmployee.getSalary());
            return emp;
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

    // Delete an employee
    public void deleteEmployee(Long id) {
        employees.removeIf(emp -> emp.getId().equals(id));
    }
}
