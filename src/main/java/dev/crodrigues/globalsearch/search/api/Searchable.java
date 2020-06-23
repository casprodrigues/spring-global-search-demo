package dev.crodrigues.globalsearch.search.api;

import java.util.List;

public interface Searchable {

    List<SearchResult> getResults(final String query);
    
}