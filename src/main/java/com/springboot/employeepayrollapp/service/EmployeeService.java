package com.springboot.employeepayrollapp.service;

import com.springboot.employeepayrollapp.model.Employee;
import com.springboot.employeepayrollapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees from DB");
        return employeeRepository.findAll();
    }

    // Add a new employee
    public Employee addEmployee(Employee employee) {
        log.info("Saving employee: {}", employee);
        return employeeRepository.save(employee);
    }

    // Update an existing employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        log.debug("Updating employee with id: {}", id);
        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            emp.setName(updatedEmployee.getName());

            emp.setSalary(updatedEmployee.getSalary());
            log.info("Updated employee with id: {}", id);
            return employeeRepository.save(emp);
        } else {
            log.error("Employee with id {} not found", id);
            throw new RuntimeException("Employee not found");
        }
    }

    // Delete an employee
    public void deleteEmployee(Long id) {
        log.warn("Deleting employee with id: {}", id);
        employeeRepository.deleteById(id);
    }
}
