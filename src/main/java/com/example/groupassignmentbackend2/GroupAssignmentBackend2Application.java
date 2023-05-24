package com.example.groupassignmentbackend2;

import com.example.groupassignmentbackend2.assignment.Customer;
import com.example.groupassignmentbackend2.assignment.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GroupAssignmentBackend2Application {

    public static void main(String[] args) {
        SpringApplication.run(GroupAssignmentBackend2Application.class, args);
    }

    @Bean
    public CommandLineRunner pojo(CustomerRepo customerRepo){
        return arg -> {
            Customer customer1 = new Customer("Anna", "897986596348795");
            Customer customer2 = new Customer("Robin", "457788943536456");
            Customer customer3 = new Customer("Daniel", "645647456786587");
            Customer customer4 = new Customer("Izabel", "867876867896796");

            customerRepo.save(customer1);
            customerRepo.save(customer2);
            customerRepo.save(customer3);
            customerRepo.save(customer4);
        };
    }

}
