package se.nackademin.repository;

import se.nackademin.Model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {

    List<Customer> findByName(String name);
    List<Customer> findBySsn(String ssn);
    List<Customer> findAll();

    @Transactional
    void deleteAllByNameAndSsn(String name, String ssn);

}
