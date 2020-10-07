package com.example.licensedtennismatches.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    default UUID nextId() {
        return UUID.randomUUID();
    }

    void store(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(UUID customerId);
}
