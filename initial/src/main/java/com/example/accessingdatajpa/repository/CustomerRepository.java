package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.model.Customer;
import com.example.accessingdatajpa.model.QCustomer;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomerRepository {
    @PersistenceContext
    private EntityManager em;


    public List<Customer> findByLastName(String lastname) {
        final JPAQuery<Customer> query = new JPAQuery<>(em);
        final QCustomer customer = QCustomer.customer;

        return query.from(customer).where(customer.lastName.eq(lastname)).fetch();
    }

    public Optional<Customer> findById (Long id) {
        final JPAQuery<Customer> query = new JPAQuery<>(em);
        final QCustomer customer = QCustomer.customer;

        return Optional.ofNullable(query.from(customer).where(customer.id.eq(id)).fetchOne());
    }

    @Transactional
    public void save(Customer customer) {
        em.merge(customer);
    }

    public List<Customer> findAll() {
        final JPAQuery<Customer> query = new JPAQuery<>(em);
        final QCustomer customer = QCustomer.customer;

        return query.from(customer).fetch();

    }
}
