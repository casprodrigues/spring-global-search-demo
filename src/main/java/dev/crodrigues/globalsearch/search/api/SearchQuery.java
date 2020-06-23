package dev.crodrigues.globalsearch.search.api;

import lombok.Value;

@Value
public final class SearchQuery {
    
    private String query;
    private long limit;

}