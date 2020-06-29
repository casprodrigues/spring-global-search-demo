package dev.crodrigues.globalsearch.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductCrudService productCrudService;

    @Autowired
    public ProductController(ProductCrudService productCrudService) {
        this.productCrudService = productCrudService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productCrudService.getProductById(id);
    }
    
}