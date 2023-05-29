package com.example.groupassignmentbackend2.service;

import com.example.groupassignmentbackend2.Model.Customer;
import com.example.groupassignmentbackend2.Model.Purchases;
import com.example.groupassignmentbackend2.assignment.repository.CustomerRepo;
import com.example.groupassignmentbackend2.exception.NotFoundCustomerException;
import com.example.groupassignmentbackend2.exception.NotSavedCustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepo dao;

    @Value("${PURCHASE_HOST}")
    private String host;
    @Value("${PURCHASE_BASE_URL}")
    private String baseUrl;

    public CustomerService(CustomerRepo dao) {
        this.dao = dao;
    }

    public List<Customer> findAllCustomer() throws NotFoundCustomerException {
        List<Customer> allCustomer = (List<Customer>) dao.findAll();
        if (allCustomer.isEmpty()) {
            throw new NotFoundCustomerException("Customer not found");
        } else {
            return allCustomer;
        }
    }

    public Boolean saveCustomer(Customer newCustomer) throws NotSavedCustomerException {
        Customer customer = dao.save(newCustomer);
        if (customer.equals(newCustomer)) {
            return true;
        } else {
            throw new NotSavedCustomerException(String.format("there is not customer with id = %s", customer.getCustomerId()));
        }
    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> customerById = dao.findById(id);
        if (customerById.isPresent()) {
            log.info("customer by id={} ", id);
            return customerById.get();
        } else {
            log.info("No Customer found by id={}", id);
            return null;
        }
    }


    public void addOrder(Purchases purchases) {


    }
}