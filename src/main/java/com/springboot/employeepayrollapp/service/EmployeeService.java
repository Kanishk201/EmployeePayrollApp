package com.springboot.employeepayrollapp.service;

import com.springboot.employeepayrollapp.dto.EmployeeDTO;
import com.springboot.employeepayrollapp.exception.EmployeeNotFoundException;
import com.springboot.employeepayrollapp.model.Employee;
import com.springboot.employeepayrollapp.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(@Valid EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, @Valid EmployeeDTO updatedEmployee) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        emp.setName(updatedEmployee.getName());
        emp.setSalary(updatedEmployee.getSalary());
        return employeeRepository.save(emp);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }
}
