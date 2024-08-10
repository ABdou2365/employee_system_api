package org.abde.employeesystem.controller;

import org.abde.employeesystem.model.Employee;
import org.abde.employeesystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody  Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public Optional<Employee> getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee) {
        return employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
    }
}
