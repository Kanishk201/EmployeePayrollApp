package com.springboot.employeepayrollapp.controller;

import com.springboot.employeepayrollapp.dto.EmployeeDTO;
import com.springboot.employeepayrollapp.model.Employee;
import com.springboot.employeepayrollapp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        log.info("Creating new employee: {}", employeeDTO.getName());
        return employeeService.addEmployee(employeeDTO);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO updatedEmployee) {
        log.info("Updating employee with ID: {}", id);
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @GetMapping("/department/sales")
    public List<Employee> getSalesEmployees() {
        return employeeService.getEmployeesFromSalesDepartment();
    }
}
