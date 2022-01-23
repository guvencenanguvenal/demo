package com.gcg.readingisgood.repository;

import com.gcg.readingisgood.model.repository.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Override
    Page<Customer> findAll(Pageable pageable);

    @Override
    Optional<Customer> findById(String id);

    Customer findByEmail(String email);
}
