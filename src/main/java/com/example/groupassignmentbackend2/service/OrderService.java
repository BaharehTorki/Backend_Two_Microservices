package com.example.groupassignmentbackend2.service;

import com.example.groupassignmentbackend2.Model.Order;
import com.example.groupassignmentbackend2.Model.OrderList;
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
    public OrderList getAllOrdet(Long customerID) {
        RestTemplate restTemplate = new RestTemplate();
        Purchases[] purchases = restTemplate.getForObject(host.concat(baseUrl).concat(customerID.toString()), Purchases[].class);
        if (purchases != null) {
            return OrderList.builder()
                    .customer(Arrays.stream(purchases)
                            .findFirst()
                            .map(purchase -> customerService.findCustomerById(purchase.getCustomerId()) ).orElse(null))
                    .orderList(Arrays.stream(purchases).map(this::map).collect(Collectors.toList()))
                    .build();
        }
        return null;
    }

    private Order map(Purchases purchases) {
        return (Objects.nonNull(purchases)) ?
                Order.builder()
                        .id(purchases.getId())
                        .date(purchases.getDate())
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
