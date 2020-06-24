package dev.crodrigues.globalsearch.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import dev.crodrigues.globalsearch.search.api.SearchQuery;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SearchController.class)
public class SearchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @Test
    public void whenValidInput_thenReturnResults() throws Exception {
        when(searchService.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("foo", 3));

        mockMvc.perform(get("/search?query=foo&limit=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists());

        final ArgumentCaptor<SearchQuery> queryArgumentCaptor = ArgumentCaptor.forClass(SearchQuery.class);
        verify(searchService).getResultsForQuery(queryArgumentCaptor.capture());
        final SearchQuery capturedQuery = queryArgumentCaptor.getValue();
        assertEquals("foo", capturedQuery.getQuery());
        assertEquals(10, capturedQuery.getLimit());
    }

    @Test
    public void whenQueryStringWithDefaultValue_thenReturnResults() throws Exception {
        when(searchService.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("foo", 3));

        mockMvc.perform(get("/search?limit=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists());

        final ArgumentCaptor<SearchQuery> queryArgumentCaptor = ArgumentCaptor.forClass(SearchQuery.class);
        verify(searchService).getResultsForQuery(queryArgumentCaptor.capture());
        final SearchQuery capturedQuery = queryArgumentCaptor.getValue();
        assertEquals("", capturedQuery.getQuery());
        assertEquals(10, capturedQuery.getLimit());
    }

    @Test
    public void whenLimitWithDefaultValue_thenReturnResults() throws Exception {
        when(searchService.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("foo", 3));

        mockMvc.perform(get("/search?query=foo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists());

        final ArgumentCaptor<SearchQuery> queryArgumentCaptor = ArgumentCaptor.forClass(SearchQuery.class);
        verify(searchService).getResultsForQuery(queryArgumentCaptor.capture());
        final SearchQuery capturedQuery = queryArgumentCaptor.getValue();
        assertEquals("foo", capturedQuery.getQuery());
        assertEquals(5, capturedQuery.getLimit());
    }
    
    @Test
    public void whenBothParametersWithDefaultValue_thenReturnResults() throws Exception {
        when(searchService.getResultsForQuery(any(SearchQuery.class)))
                .thenReturn(SearchTestUtils.buildSearchResultsWithPrefix("foo", 3));

        mockMvc.perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists());

        final ArgumentCaptor<SearchQuery> queryArgumentCaptor = ArgumentCaptor.forClass(SearchQuery.class);
        verify(searchService).getResultsForQuery(queryArgumentCaptor.capture());
        final SearchQuery capturedQuery = queryArgumentCaptor.getValue();
        assertEquals("", capturedQuery.getQuery());
        assertEquals(5, capturedQuery.getLimit());
    }

    @Test
    public void whenLimitValueIsZero_thenReturnBadRequest() throws Exception {
        mockMvc.perform(get("/search?query=foo&limit=0"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(searchService);
    }

    @Test
    public void whenLimitValueIsBiggerThanExpected_thenReturnBadRequest() throws Exception {
        mockMvc.perform(get("/search?query=foo&limit=9999"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(searchService);
    }

}