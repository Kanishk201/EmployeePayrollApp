package com.springboot.employeepayrollapp.repository;

import com.springboot.employeepayrollapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE 'Sales' MEMBER OF e.department")
    List<Employee> findEmployeesBySalesDepartment();
}
