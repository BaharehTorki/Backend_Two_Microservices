package com.example.groupassignmentbackend2.assignment;

import com.example.groupassignmentbackend2.exception.NotFoundCustomerException;
import com.example.groupassignmentbackend2.exception.NotSavedCustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepo dao;

    public CustomerService(CustomerRepo dao){
        this.dao = dao;
    }

    public List<Customer> findAllCustomer() throws NotFoundCustomerException {
        List<Customer> allCustomer = (List<Customer>) dao.findAll();
        if (allCustomer.isEmpty()){
            throw new NotFoundCustomerException("Customer not found");
        }else {
            return allCustomer;
        }
    }

    public Boolean saveCustomer(Customer newCustomer) throws NotSavedCustomerException {
        Customer customer = dao.save(newCustomer);
        if (customer.equals(newCustomer)){
            return true;
        }else {
            throw new NotSavedCustomerException();
        }
    }

    public Customer findCustomerById(Long id) throws NotFoundCustomerException {
        Optional<Customer> customerById = dao.findById(id);
        if (customerById.isPresent()){
            log.info("customer by id={} ", id);
            return customerById.get();
        } else {
            throw new NotFoundCustomerException(String.format("There is not customer with id = %s", id));
        }
    }
}