package dev.crodrigues.globalsearch.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.crodrigues.globalsearch.search.api.SearchResult;

public class CustomerConverterTest {
    
    CustomerConverter customerConverter;

    @BeforeEach
    void init() {
        customerConverter = new CustomerConverter();
    }

    @Test
    void whenConvertingCustomer_thenReturnSearchResult() {
        final Customer fakeCustomer = createFakeCustomer();

        final SearchResult searchResult = customerConverter.convertCustomerToSearchResult(fakeCustomer);

        assertEquals("customer", searchResult.getEntityType());
        assertEquals("foo bar", searchResult.getTitle());
        assertEquals("foo@bar.com 5512341234", searchResult.getExcerpt());
        assertTrue(searchResult.getUrl().getHref().endsWith("1"));
    }

    private Customer createFakeCustomer() {
        final Customer fakeCustomer = new Customer();

        fakeCustomer.setId(1);
        fakeCustomer.setLastName("bar");
        fakeCustomer.setFirstName("foo");
        fakeCustomer.setEmail("foo@bar.com");
        fakeCustomer.setCompany("test company");
        fakeCustomer.setPhone("5512341234");
        fakeCustomer.setAddress1("address 1");
        fakeCustomer.setAddress2("address 2");
        fakeCustomer.setCity("city");
        fakeCustomer.setState("state");
        fakeCustomer.setPostalCode("123456");
        fakeCustomer.setCountry("country");

        return fakeCustomer;
    }

}