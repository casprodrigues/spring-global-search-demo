package dev.crodrigues.globalsearch.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeCrudService employeeCrudService;

    @Autowired
    public EmployeeController(EmployeeCrudService employeeCrudService) {
        this.employeeCrudService = employeeCrudService;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeCrudService.getEmployeeById(id);
    }
    
}