package com.workforcepro.workforce_pro_backend.repository;

import com.workforcepro.workforce_pro_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
