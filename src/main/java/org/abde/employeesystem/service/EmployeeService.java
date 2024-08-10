package org.abde.employeesystem.service;

import org.abde.employeesystem.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    void deleteEmployee(long id);

    Optional<Employee> getEmployeeById(Long id);

    Employee updateEmployee(long id,Employee employee);
}
