package com.springboot.employeepayrollapp.controller;

import com.springboot.employeepayrollapp.model.Employee;
import com.springboot.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Create an employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // PUT - Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setSalary(updatedEmployee.getSalary());
                    return ResponseEntity.ok(employeeRepository.save(employee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PATCH - Partial update (e.g., update only salary)
    @PatchMapping("/{id}/salary")
    public ResponseEntity<Employee> updateEmployeeSalary(@PathVariable Long id, @RequestParam Double salary) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setSalary(salary);
                    return ResponseEntity.ok(employeeRepository.save(employee));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE - Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Delete all employees
    @DeleteMapping
    public ResponseEntity<Void> deleteAllEmployees() {
        employeeRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
