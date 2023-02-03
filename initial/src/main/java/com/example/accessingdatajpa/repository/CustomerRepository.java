package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName (String lastname);

    Optional<Customer> findById (Long id);
}
