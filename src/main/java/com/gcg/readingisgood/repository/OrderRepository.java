package com.gcg.readingisgood.repository;

import com.gcg.readingisgood.model.repository.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    @Override
    Optional<Order> findById(String s);

    List<Order> findByCustomerId(String customerId);

    List<Order> findByOrderDateBetween(Date from, Date to);

}
