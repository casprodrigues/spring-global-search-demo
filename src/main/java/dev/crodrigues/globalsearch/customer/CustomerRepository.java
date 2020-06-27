package dev.crodrigues.globalsearch.customer;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining
            (String firstName, String lastName, Pageable pageable);
    
}