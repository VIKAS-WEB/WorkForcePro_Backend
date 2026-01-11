package com.workforcepro.workforce_pro_backend.controller;

import com.workforcepro.workforce_pro_backend.entity.Employee;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final com.workforcepro.workforce_pro_backend.service.EmployeeService service;

    public EmployeeController(com.workforcepro.workforce_pro_backend.service.EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/AddEmployee")
    public Employee addEmployee(@RequestBody Employee emp) {
        return service.addEmployee(emp);
    }

    @PostMapping(value = "/{id}/uploadImage", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Employee uploadImage(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image) throws java.io.IOException {
        return service.uploadImage(id, image);
    }

    @GetMapping("/FetchEmployeeList")
    public java.util.List<Employee> getEmployees() {
        return service.getEmployees();
    }
}
