package com.springboot.employeepayrollapp.service;

import com.springboot.employeepayrollapp.dto.EmployeeDTO;
import com.springboot.employeepayrollapp.exception.EmployeeNotFoundException;
import com.springboot.employeepayrollapp.model.Employee;
import com.springboot.employeepayrollapp.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees from the database");
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
    }

    public Employee addEmployee(@Valid EmployeeDTO employeeDTO) {
        log.info("Adding a new employee: {}", employeeDTO.getName());
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setGender(employeeDTO.getGender());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setNote(employeeDTO.getNote());
        employee.setProfilePic(employeeDTO.getProfilePic());
        employee.setDepartment(employeeDTO.getDepartment());

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, @Valid EmployeeDTO updatedEmployee) {
        log.info("Updating employee with ID: {}", id);
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        emp.setName(updatedEmployee.getName());
        emp.setSalary(updatedEmployee.getSalary());
        emp.setGender(updatedEmployee.getGender());
        emp.setStartDate(updatedEmployee.getStartDate());
        emp.setNote(updatedEmployee.getNote());
        emp.setProfilePic(updatedEmployee.getProfilePic());
        emp.setDepartment(updatedEmployee.getDepartment());

        return employeeRepository.save(emp);
    }
}
