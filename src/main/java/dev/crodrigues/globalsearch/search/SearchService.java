package dev.crodrigues.globalsearch.search;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.crodrigues.globalsearch.search.api.SearchQuery;
import dev.crodrigues.globalsearch.search.api.SearchResult;
import dev.crodrigues.globalsearch.search.api.Searchable;

@Service
public class SearchService {
    
    private final List<Searchable> searchableImplementations;

    @Autowired
    public SearchService(List<Searchable> searchableImplementations) {
        this.searchableImplementations = searchableImplementations;
    }

    public List<SearchResult> getResultsForQuery(final SearchQuery query) {
        return searchableImplementations
                .stream()
                .map(searchable -> searchable.getResultsForQuery(query))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .limit(query.getLimit())
                .collect(Collectors.toList());
    }

}