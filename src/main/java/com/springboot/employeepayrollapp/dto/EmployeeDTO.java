package com.springboot.employeepayrollapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EmployeeDTO {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    private String name;
    private Double salary;

    // Explicit constructor to fix the issue
    public EmployeeDTO(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }
}
