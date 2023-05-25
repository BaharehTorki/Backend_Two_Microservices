package com.example.groupassignmentbackend2.assignment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;
    private String name;
    private String accountNumber;


    public Customer(String name, String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
    }


}