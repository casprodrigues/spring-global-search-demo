package dev.crodrigues.globalsearch.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.crodrigues.globalsearch.exceptions.ResourceNotFoundException;

@Service
public class EmployeeCrudService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeCrudService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(final Integer id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
    
}