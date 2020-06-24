package dev.crodrigues.globalsearch.search.api;

import java.util.List;

public interface Searchable {

    List<SearchResult> getResultsForQuery(final SearchQuery query);
    
}