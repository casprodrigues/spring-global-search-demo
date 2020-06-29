package dev.crodrigues.globalsearch.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import dev.crodrigues.globalsearch.search.api.SearchResult;

@Component
public class ProductConverter {
    
    public SearchResult convertProductToSearchResult(final Product product) {
        return SearchResult.builder()
                .entityType("product")
                .title(product.getProductName())
                .excerpt(product.getProductCodeAndPriceAsString())
                .url(buildLinkToProduct(product))
                .build();
    }

    private Link buildLinkToProduct(final Product product) {
        return linkTo(ProductController.class)
                .slash(product.getId())
                .withSelfRel();
    }

}