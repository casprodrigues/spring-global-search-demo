package dev.crodrigues.globalsearch.customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import dev.crodrigues.globalsearch.search.api.SearchResult;

@Component
public class CustomerConverter {

    public SearchResult convertCustomerToSearchResult(final Customer customer) {
        final String title = String.format("%s %s", customer.getFirstName(), customer.getLastName());
        final String excerpt = String.format("%s %s", customer.getEmail(), customer.getPhone());
        final Link url = linkTo(CustomerController.class).slash(customer.getId()).withSelfRel();

        return SearchResult.builder()
                .entityType(customer.getClass().getSimpleName())
                .title(title)
                .excerpt(excerpt)
                .url(url)
                .build();
    }
    
}