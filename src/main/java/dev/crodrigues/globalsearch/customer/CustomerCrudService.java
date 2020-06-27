package dev.crodrigues.globalsearch.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCrudService {
    
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerCrudService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(final Integer id) {
        return customerRepository
                .findById(id)
                .orElse(null); // TODO throw exception
    }

}