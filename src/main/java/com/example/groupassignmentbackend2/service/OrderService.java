package com.example.groupassignmentbackend2.service;

import com.example.groupassignmentbackend2.Model.Order;
import com.example.groupassignmentbackend2.Model.Purchases;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    ItemService itemService;
    @Autowired
    CustomerService customerService;


    @Value("${PURCHASE_HOST}")
    private String host;
    @Value("${PURCHASE_BASE_URL}")
    private String baseUrl;
    public List<Order> getAllOrdet(Long customerID) {
        RestTemplate restTemplate = new RestTemplate();
        Purchases[] objects = restTemplate.getForObject(host.concat(baseUrl).concat(customerID.toString()), Purchases[].class);
        if (objects != null) {
            return Arrays.stream(objects).map(this::map).collect(Collectors.toList());
        }
        return null;
    }

    private Order map(Purchases purchases) {
        return (Objects.nonNull(purchases)) ?
                Order.builder()
                        .id(purchases.getId())
                        .date(purchases.getDate())
                        .customer(customerService.findCustomerById(purchases.getCustomerId()))
                        .itemList(
                                (Objects.nonNull(purchases.getItems())) ?
                                        purchases.getItems().stream()
                                                .filter(Objects::nonNull)
                                                .map(item -> itemService.getItemById(item))
                                                .collect(Collectors.toList())
                                        : null)
                        .build()
                : null;
    }


}
