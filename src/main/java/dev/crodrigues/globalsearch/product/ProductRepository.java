package dev.crodrigues.globalsearch.product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    
    List<Product> findByProductCodeIgnoreCaseContainingOrProductNameIgnoreCaseContaining
            (String productCode, String productName, Pageable pageable);

}