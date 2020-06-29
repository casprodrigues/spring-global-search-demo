package dev.crodrigues.globalsearch.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.crodrigues.globalsearch.exceptions.ResourceNotFoundException;

@Service
public class ProductCrudService {
    
    private final ProductRepository productRepository;

    @Autowired
    public ProductCrudService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(final Integer id) {
        return productRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

}