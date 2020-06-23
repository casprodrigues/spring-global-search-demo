package dev.crodrigues.globalsearch.search.api;

import org.springframework.hateoas.Link;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public final class SearchResult {
    
    private String entityType;
    private String title;
    private String excerpt;
    private Link url;

}