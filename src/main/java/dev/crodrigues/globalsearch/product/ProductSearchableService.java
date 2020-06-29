package dev.crodrigues.globalsearch.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.crodrigues.globalsearch.search.api.SearchQuery;
import dev.crodrigues.globalsearch.search.api.SearchResult;
import dev.crodrigues.globalsearch.search.api.Searchable;

@Service
public class ProductSearchableService implements Searchable {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Autowired
    public ProductSearchableService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public List<SearchResult> getResultsForQuery(SearchQuery query) {
        final String queryStr = query.getQuery();
        final Pageable pageRequest = query.asPageRequest();

        return productRepository
                .findByProductCodeIgnoreCaseContainingOrProductNameIgnoreCaseContaining(queryStr, queryStr, pageRequest)
                .stream()
                .map(productConverter::convertProductToSearchResult)
                .collect(Collectors.toList());
    }

}