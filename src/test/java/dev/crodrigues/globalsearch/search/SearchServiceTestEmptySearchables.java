package dev.crodrigues.globalsearch.search;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.crodrigues.globalsearch.search.api.SearchQuery;
import dev.crodrigues.globalsearch.search.api.SearchResult;

public class SearchServiceTestEmptySearchables {

    SearchService searchService;

    @BeforeEach
    void init() {
        searchService = new SearchService(Collections.emptyList());
    }
    
    @Test
    void whenSearchableListIsNull_thenEmptyResults() {
        final SearchQuery query = new SearchQuery("query", Long.valueOf(10));

        List<SearchResult> searchResults = searchService.getResultsForQuery(query);

        assertTrue(searchResults.isEmpty());
    }

}