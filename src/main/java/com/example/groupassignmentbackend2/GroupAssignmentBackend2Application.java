package com.example.groupassignmentbackend2;

import com.example.groupassignmentbackend2.Model.Customer;
import com.example.groupassignmentbackend2.assignment.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class GroupAssignmentBackend2Application {

    public static void main(String[] args) {
        log.info("Something");
        System.out.println("something");
        SpringApplication.run(GroupAssignmentBackend2Application.class, args);
    }

   /* @Bean
    public CommandLineRunner pojo(CustomerRepo customerRepo){
        return arg -> {
            customerRepo.save(new Customer("Anna", "897986596348795"));
            customerRepo.save(new Customer("Robin", "2342342342"));
            customerRepo.save(new Customer("Daniel", "234524324323"));
            customerRepo.save(new Customer("Emma", "89234234348795"));
            customerRepo.save(new Customer("Rodin", "457788943536456"));
            customerRepo.save(new Customer("Dan", "64564745686587"));
            customerRepo.save(new Customer("Ali", "89798659348795"));
            customerRepo.save(new Customer("Rosmos", "47894536456"));
            customerRepo.save(new Customer("Thomas", "645456786587"));
            customerRepo.save(new Customer("Sebastian", "986596348795"));
            customerRepo.save(new Customer("Ro", "45788946456"));
            customerRepo.save(new Customer("Lion", "645647786587"));
            customerRepo.save(new Customer("Isak", "86787696796"));
        };
    }*/

}
