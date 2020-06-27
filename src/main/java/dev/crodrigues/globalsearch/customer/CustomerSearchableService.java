package dev.crodrigues.globalsearch.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.crodrigues.globalsearch.search.api.SearchQuery;
import dev.crodrigues.globalsearch.search.api.SearchResult;
import dev.crodrigues.globalsearch.search.api.Searchable;

@Service
public class CustomerSearchableService implements Searchable {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerSearchableService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    @Override
    public List<SearchResult> getResultsForQuery(SearchQuery query) {
        final String queryStr = query.getQuery();
        final Pageable pageRequest = PageRequest.of(0, Math.toIntExact(query.getLimit()));

        return customerRepository
                .findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(queryStr, queryStr, pageRequest)
                .stream()
                .map(customerConverter::convertCustomerToSearchResult)
                .collect(Collectors.toList());
    }
    
}