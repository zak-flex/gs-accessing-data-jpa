package com.example.accessingdatajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.accessingdatajpa.model.Customer;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customers;

    @BeforeEach
    void createDummyData() {
        entityManager.persist(customer("Jack", "Bauer"));
        entityManager.persist(customer("Chloe", "O'Brian"));
        entityManager.persist(customer("Kim", "Bauer"));
        entityManager.persist(customer("David", "Palmer"));
        entityManager.persist(customer("Michelle", "Dessler"));
    }

    @Test
    public void testFindByLastName() {
        List<Customer> findByLastName = customers.findByLastName("Bauer");
        assertThat(findByLastName).extracting(Customer::lastName).containsOnly("Bauer");
    }

    @Test
    void findById () {
        Optional<Customer> customer = customers.findById(1L);
        assertThat(customer.orElseThrow().id()).isEqualTo(1L);
    }

    private static Customer customer(String firstName, String lastName) {
        return Customer.builder().firstName(firstName).lastName(lastName).build();
    }

}
