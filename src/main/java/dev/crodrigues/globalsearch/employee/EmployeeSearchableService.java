package dev.crodrigues.globalsearch.employee;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.crodrigues.globalsearch.search.api.SearchQuery;
import dev.crodrigues.globalsearch.search.api.SearchResult;
import dev.crodrigues.globalsearch.search.api.Searchable;

@Service
public class EmployeeSearchableService implements Searchable {

    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    @Autowired
    public EmployeeSearchableService(EmployeeRepository employeeRepository, EmployeeConverter employeeConverter) {
        this.employeeRepository = employeeRepository;
        this.employeeConverter = employeeConverter;
    }

    @Override
    public List<SearchResult> getResultsForQuery(SearchQuery query) {
        final String queryStr = query.getQuery();
        final Pageable pageRequest = query.asPageRequest();

        return employeeRepository
                .findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(queryStr, queryStr, pageRequest)
                .stream()
                .map(employeeConverter::convertEmployeeToSearchResult)
                .collect(Collectors.toList());
    }
    
}