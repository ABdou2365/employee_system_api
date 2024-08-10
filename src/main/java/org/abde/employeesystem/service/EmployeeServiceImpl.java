package org.abde.employeesystem.service;

import org.abde.employeesystem.entity.EmployeeEntity;
import org.abde.employeesystem.model.Employee;
import org.abde.employeesystem.repository.EmployeeRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepo.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
        List<Employee> employees = employeeEntities.stream()
                .map(emp -> new Employee(emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmail()))
                .toList();
        return employees;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepo.findById(id);

        if (employeeEntityOptional.isPresent()) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeEntityOptional.get(), employee);
            return Optional.of(employee);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepo.findById(id);

        if (employeeEntityOptional.isPresent()) {
            EmployeeEntity existingEmployee = employeeEntityOptional.get();
            BeanUtils.copyProperties(employee, existingEmployee, "id"); // copy properties, excluding the "id"
            employeeRepo.save(existingEmployee); // save the updated entity
            return new Employee(existingEmployee.getId(),
                    existingEmployee.getFirstName(),
                    existingEmployee.getLastName(),
                    existingEmployee.getEmail());
        } else {
            // Handle the case where the employee does not exist
            return null; // or throw an exception depending on your error handling strategy
        }
    }

}
