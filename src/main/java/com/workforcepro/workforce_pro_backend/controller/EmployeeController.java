package com.workforcepro.workforce_pro_backend.controller;

import com.workforcepro.workforce_pro_backend.entity.Employee;
import com.workforcepro.workforce_pro_backend.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee emp) {
        return repo.save(emp);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return repo.findAll();
    }
}
