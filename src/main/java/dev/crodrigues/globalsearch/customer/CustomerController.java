package dev.crodrigues.globalsearch.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerCrudService customerCrudService;

    @Autowired
    public CustomerController(CustomerCrudService customerService) {
        this.customerCrudService = customerService;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerCrudService.getCustomerById(id);
    }
    
}