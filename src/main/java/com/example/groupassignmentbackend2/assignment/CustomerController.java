package com.example.groupassignmentbackend2.assignment;

import com.example.groupassignmentbackend2.exception.NotFoundCustomerException;
import com.example.groupassignmentbackend2.exception.NotSavedCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customer/all")
    public List<Customer> getAllCustomers() throws NotFoundCustomerException {
        return customerService.findAllCustomer();
    }

    @RequestMapping("customer/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) throws NotFoundCustomerException {
        return customerService.findCustomerById(id);

    }

    @PostMapping("customer/add")
    public Boolean addCustomer(@RequestBody Customer newCustomer) throws NotSavedCustomerException {
        return customerService.saveCustomer(newCustomer);
    }
}