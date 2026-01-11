package com.workforcepro.workforce_pro_backend.service;

import com.workforcepro.workforce_pro_backend.entity.Employee;
import com.workforcepro.workforce_pro_backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee addEmployee(Employee emp) {
        if (!repo.findByEmail(emp.getEmail()).isEmpty()) {
            throw new com.workforcepro.workforce_pro_backend.exception.EmployeeAlreadyExistsException(
                    "Employee with email " + emp.getEmail() + " already exists");
        }
        emp.setEmployeeIdNumber(String.format("%04d", new java.util.Random().nextInt(10000)));
        return repo.save(emp);
    }

    public Employee uploadImage(Long id, org.springframework.web.multipart.MultipartFile image)
            throws java.io.IOException {
        Employee emp = repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        if (image != null && !image.isEmpty()) {
            String uploadDir = System.getProperty("user.home") + "/workforce_uploads/";
            java.io.File directory = new java.io.File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fileName = java.util.UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            java.nio.file.Path filePath = java.nio.file.Paths.get(uploadDir + fileName);
            java.nio.file.Files.copy(image.getInputStream(), filePath);
            emp.setProfileImage(fileName);
        }
        return repo.save(emp);
    }

    public List<Employee> getEmployees() {
        return repo.findAll();
    }
}
