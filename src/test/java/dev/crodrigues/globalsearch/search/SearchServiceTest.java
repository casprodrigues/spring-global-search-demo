package dev.crodrigues.globalsearch.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.crodrigues.globalsearch.search.api.SearchQuery;
import dev.crodrigues.globalsearch.search.api.SearchResult;
import dev.crodrigues.globalsearch.search.api.Searchable;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    
    SearchService searchService;

    @Mock
    Searchable mockSearchable1;

    @Mock
    Searchable mockSearchable2;

    @Mock
    Searchable mockSearchable3;

    @Mock
    Searchable mockSearchable4;

    @BeforeEach
    void init() {
        final List<Searchable> mockSearchableList = 
                Arrays.asList(mockSearchable1, mockSearchable2, mockSearchable3, mockSearchable4);
        searchService = new SearchService(mockSearchableList);
    }

    @Test
    void whenAllSearchablesReturnsEmptyList_thenEmptyResults() {
        when(mockSearchable1.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(Collections.emptyList());
        when(mockSearchable2.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(Collections.emptyList());
        when(mockSearchable3.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(Collections.emptyList());
        when(mockSearchable4.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(Collections.emptyList());

        final long limit = 10;
        final SearchQuery query = new SearchQuery("query", limit);
        List<SearchResult> searchResults = searchService.getResultsForQuery(query);

        assertTrue(searchResults.isEmpty());
        verify(mockSearchable1).getResultsForQuery(query);
        verify(mockSearchable2).getResultsForQuery(query);
        verify(mockSearchable3).getResultsForQuery(query);
        verify(mockSearchable4).getResultsForQuery(query);
    }

    @Test
    void whenAllSearchableReturnsNull_thenEmptyResults() {
        when(mockSearchable1.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(null);
        when(mockSearchable2.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(null);
        when(mockSearchable3.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(null);
        when(mockSearchable4.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(null);

        final long limit = 10;
        final SearchQuery query = new SearchQuery("query", limit);
        List<SearchResult> searchResults = searchService.getResultsForQuery(query);

        assertTrue(searchResults.isEmpty());
        verify(mockSearchable1).getResultsForQuery(query);
        verify(mockSearchable2).getResultsForQuery(query);
        verify(mockSearchable3).getResultsForQuery(query);
        verify(mockSearchable4).getResultsForQuery(query);
    }

    @Test
    void whenSearchableReturnsResults_thenReturnResults() {
        when(mockSearchable1.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("Mock1", 1));
        when(mockSearchable2.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("Mock2", 1));
        when(mockSearchable3.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("Mock3", 1));
        when(mockSearchable4.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("Mock4", 1));

        final long limit = 10;
        final SearchQuery query = new SearchQuery("query", limit);
        List<SearchResult> searchResults = searchService.getResultsForQuery(query);

        assertEquals(4, searchResults.size());
        assertEquals("Mock1 1", searchResults.get(0).getTitle());
        assertEquals("Mock2 1", searchResults.get(1).getTitle());
        assertEquals("Mock3 1", searchResults.get(2).getTitle());
        assertEquals("Mock4 1", searchResults.get(3).getTitle());
        verify(mockSearchable1).getResultsForQuery(query);
        verify(mockSearchable2).getResultsForQuery(query);
        verify(mockSearchable3).getResultsForQuery(query);
        verify(mockSearchable4).getResultsForQuery(query);
    }

    @Test
    void whenSearchableReturnsExceedLimits_thenDoNotInteractWithSubsequentSearchables() {
        when(mockSearchable1.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("Mock1", 8));
        when(mockSearchable2.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("Mock2", 8));

        final long limit = 10;
        final SearchQuery query = new SearchQuery("query", limit);
        List<SearchResult> searchResults = searchService.getResultsForQuery(query);

        assertEquals(limit, searchResults.size());
        assertEquals("Mock1 1", searchResults.get(0).getTitle());
        assertEquals("Mock1 2", searchResults.get(1).getTitle());
        assertEquals("Mock1 3", searchResults.get(2).getTitle());
        assertEquals("Mock1 4", searchResults.get(3).getTitle());
        assertEquals("Mock1 5", searchResults.get(4).getTitle());
        assertEquals("Mock1 6", searchResults.get(5).getTitle());
        assertEquals("Mock1 7", searchResults.get(6).getTitle());
        assertEquals("Mock1 8", searchResults.get(7).getTitle());
        assertEquals("Mock2 1", searchResults.get(8).getTitle());
        assertEquals("Mock2 2", searchResults.get(9).getTitle());
        verify(mockSearchable1).getResultsForQuery(query);
        verify(mockSearchable2).getResultsForQuery(query);
        verifyNoInteractions(mockSearchable3);
        verifyNoInteractions(mockSearchable4);
    }

}