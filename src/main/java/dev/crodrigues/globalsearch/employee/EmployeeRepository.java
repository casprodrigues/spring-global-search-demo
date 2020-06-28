package dev.crodrigues.globalsearch.employee;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining
            (String firstName, String lastName, Pageable pageable);
    
}