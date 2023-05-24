package com.example.groupassignmentbackend2.assignment;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

    List<Customer> findByName(String name);
    List<Customer> findByAccountNumber(String accountNumber);
    List<Customer> findAll();

    @Transactional
    void deleteAllByNameAndAccountNumber(String name, String accountNumber);

}
