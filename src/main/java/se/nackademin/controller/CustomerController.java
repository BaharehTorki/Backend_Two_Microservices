package se.nackademin.controller;

import se.nackademin.Model.Customer;
import se.nackademin.Model.OrderList;
import se.nackademin.Model.Purchases;
import se.nackademin.exception.NotSavedCustomerException;
import se.nackademin.service.CustomerService;
import se.nackademin.service.OrderService;
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
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomer();
    }

    @RequestMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return customerService.findCustomerById(id);

    }

    @PostMapping("/add")
    public Boolean addCustomer(@RequestBody Customer newCustomer) throws NotSavedCustomerException {
        return customerService.saveCustomer(newCustomer);
    }

    @RequestMapping("/{id}/orders")
    public OrderList getAllOrderByCustomerId(@PathVariable("id") Long id) {
        return orderService.getAllOrder(id);
    }

    @PostMapping("/addOrder")
    public Response addOrder(@RequestBody Purchases purchases)  {
        Response response = new Response();
        try {
            orderService.addOrder(purchases);
            response.setStatus(200);
            return response;
        } catch (Exception e) {
            response.setStatus(500);
            return response;
        }
    }
}