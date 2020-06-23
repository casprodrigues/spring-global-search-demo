package dev.crodrigues.globalsearch.search;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchSmokeTest {
    
    @Autowired
    private SearchController searchController;

    @Autowired
    private SearchService searchService;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(searchController);
        assertNotNull(searchService);
    }

}