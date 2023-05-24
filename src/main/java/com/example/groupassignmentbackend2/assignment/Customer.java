package com.example.groupassignmentbackend2.assignment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private long customerId;
    private String name;
    private String accountNumber;


    public Customer(long customerId, String name, String accountNumber) {
        this.customerId = customerId;
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public Customer(String name, String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }


}