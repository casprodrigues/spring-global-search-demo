package dev.crodrigues.globalsearch.search.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Value;

@Value
public final class SearchQuery {

    private static String DEFAULT_QUERY = "";
    private static long DEFAULT_LIMIT = 5;

    @Size(max = 100)
    private String query;

    @Positive
    @Max(100)
    private long limit;

    public SearchQuery(String query, Long limit) {
        this.query = query == null ? DEFAULT_QUERY : query;
        this.limit = limit == null ? DEFAULT_LIMIT : limit.longValue();
    }

}