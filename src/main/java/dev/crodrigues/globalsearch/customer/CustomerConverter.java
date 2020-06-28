package dev.crodrigues.globalsearch.customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import dev.crodrigues.globalsearch.search.api.SearchResult;

@Component
public class CustomerConverter {

    public SearchResult convertCustomerToSearchResult(final Customer customer) {
        return SearchResult.builder()
                .entityType("customer")
                .title(customer.getFullName())
                .excerpt(customer.getContactDetailsAsString())
                .url(buildLinkToCustomer(customer))
                .build();
    }

    private Link buildLinkToCustomer(final Customer customer) {
        return linkTo(CustomerController.class)
                .slash(customer.getId())
                .withSelfRel();
    }
    
}