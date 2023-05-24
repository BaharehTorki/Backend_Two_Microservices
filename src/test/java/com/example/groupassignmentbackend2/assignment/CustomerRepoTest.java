package com.example.groupassignmentbackend2.assignment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepoTest {

    public static final String CUSTOMER_NAME = "customer_name_test";
    public static final String CUSTOMER_ACCOUNT_NUMBER = "customer_account_number";
    public static final String UPDATE_CUSTOMER_NAME = "UPDATE_CUSTOMER_NAME";
    private final Customer TEST_CUSTOMER = new Customer(CUSTOMER_NAME, CUSTOMER_ACCOUNT_NUMBER);

    private Customer savedCustomer;
    @Autowired
    CustomerRepo sut;

    @BeforeEach
    void setUp() {
        System.out.println("================== Add a new Customer before running the test");
        savedCustomer = sut.save(TEST_CUSTOMER);
    }

    @AfterEach
    void afterEach() {
        sut.deleteAllByNameAndAccountNumber(CUSTOMER_NAME, CUSTOMER_ACCOUNT_NUMBER);
        System.out.println("================== delete the new Customer before running the test");
    }

    @Test
    void should_save_into_database() {
        assertEquals(TEST_CUSTOMER, savedCustomer);
    }

    @Test
    void should_find_customer_by_id() {
        long customerId = savedCustomer.getCustomerId();
        Optional<Customer> actualOptional = sut.findById(customerId);
        assertNotNull(actualOptional.get());
        Customer actual = actualOptional.get();
        assertEquals(CUSTOMER_NAME, actual.getName());
        assertEquals(CUSTOMER_ACCOUNT_NUMBER, actual.getAccountNumber());
    }

    @Test
    void should_find_customer_by_name() {
        //Adding another customer with same property in database to check the functionality
        sut.save(new Customer(CUSTOMER_NAME, "ANOTHER_CUSTOMER_ACCOUNT_NUMBER"));

        List<Customer> actual = sut.findByName(CUSTOMER_NAME);
        assertNotNull(actual);
        assertEquals(2, actual.size());
    }

    @Test
    void should_find_customer_by_accountNumber() {
        sut.save(new Customer("ANOTHER_NAME", CUSTOMER_ACCOUNT_NUMBER));

        List<Customer> actual = sut.findByAccountNumber(CUSTOMER_ACCOUNT_NUMBER);
        assertNotNull(actual);
        assertEquals(2, actual.size());
    }

    @Test
    void should_return_all_customer() {
        List<Customer> actual = sut.findAll();
        assertNotNull(actual);
        assertNotEquals(0,actual.size());
    }

    @Test
    void should_update() {
     /*   Customer savedCustomer = sut.findById(1L).get();
        savedCustomer.setName(UPDATE_CUSTOMER_NAME);
*/
    }

    @Test
    void should_delete() {
    }
}