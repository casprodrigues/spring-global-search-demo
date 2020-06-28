package dev.crodrigues.globalsearch.search.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.Value;

@Value
public final class SearchQuery {

    private static final String DEFAULT_QUERY = "";
    private static final long DEFAULT_LIMIT = 5;

    @Size(max = 100)
    private String query;

    @Positive
    @Max(100)
    private long limit;

    public SearchQuery(String query, Long limit) {
        this.query = query == null ? DEFAULT_QUERY : query;
        this.limit = limit == null ? DEFAULT_LIMIT : limit.longValue();
    }

    public Pageable asPageRequest() {
        return PageRequest.of(0, Math.toIntExact(limit));
    }

}