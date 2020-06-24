package dev.crodrigues.globalsearch.search;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.crodrigues.globalsearch.search.api.SearchQuery;
import dev.crodrigues.globalsearch.search.api.SearchResult;

@RestController
@RequestMapping("/search")
public class SearchController {
    
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<SearchResult> getResultsForQuery(@Valid final SearchQuery query) {
        return searchService.getResultsForQuery(query);
    }

}