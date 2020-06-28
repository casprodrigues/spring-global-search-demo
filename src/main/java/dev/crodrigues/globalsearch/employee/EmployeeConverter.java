package dev.crodrigues.globalsearch.employee;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import dev.crodrigues.globalsearch.search.api.SearchResult;

@Component
public class EmployeeConverter {
    
    public SearchResult convertEmployeeToSearchResult(final Employee employee) {
        return SearchResult.builder()
                .entityType("employee")
                .title(employee.getFullName())
                .excerpt(employee.getJobDetailsAsString())
                .url(buildLinkToEmployee(employee))
                .build();
    }

    private Link buildLinkToEmployee(final Employee employee) {
        return linkTo(EmployeeController.class)
                .slash(employee.getId())
                .withSelfRel();
    }

}