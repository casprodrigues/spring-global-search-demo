package dev.crodrigues.globalsearch.search;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dev.crodrigues.globalsearch.search.api.SearchResult;

public final class SearchTestUtils {

    public static List<SearchResult> buildSearchResultsWithPrefix(final String prefix, final int qtySearchResults) {
        return IntStream.rangeClosed(1, qtySearchResults)
                .mapToObj(i -> buildNthSearchResultWithPrefix(i, prefix))
                .collect(Collectors.toList());
    }

    private static SearchResult buildNthSearchResultWithPrefix(final int i, final String prefix) {
        return SearchResult.builder()
                .entityType("mock")
                .title(String.format("%s %d", prefix, i))
                .excerpt(String.format("Excerpt for %s %d", prefix, i))
                .build();
    }
    
}