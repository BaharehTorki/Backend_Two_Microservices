package com.example.groupassignmentbackend2.assignment.controller;

import com.example.groupassignmentbackend2.Model.Customer;
import com.example.groupassignmentbackend2.Model.OrderList;
import com.example.groupassignmentbackend2.Model.Purchases;
import com.example.groupassignmentbackend2.exception.NotFoundCustomerException;
import com.example.groupassignmentbackend2.exception.NotSavedCustomerException;
import com.example.groupassignmentbackend2.service.CustomerService;
import com.example.groupassignmentbackend2.service.OrderService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;
    private OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() throws NotFoundCustomerException {
        return customerService.findAllCustomer();
    }

    @RequestMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) throws NotFoundCustomerException {
        return customerService.findCustomerById(id);

    }

    @PostMapping("/add")
    public Boolean addCustomer(@RequestBody Customer newCustomer) throws NotSavedCustomerException {
        return customerService.saveCustomer(newCustomer);
    }

    @RequestMapping("/{id}/orders")
    public OrderList getAllOrderByCustomerId(@PathVariable("id") Long id) {
        return orderService.getAllOrdet(id);
    }

    @PostMapping("/addOrder")
    public Response addOrder(@RequestBody Purchases purchases) {
        Response response = new Response();
        try {
            customerService.addOrder(purchases);
            response.setStatus(200);
            return response;
        } catch (Exception e) {
            response.setStatus(500);
            return response;
        }
    }
}