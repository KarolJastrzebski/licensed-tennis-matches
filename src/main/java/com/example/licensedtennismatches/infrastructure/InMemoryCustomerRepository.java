package com.example.licensedtennismatches.infrastructure;

import com.example.licensedtennismatches.model.Customer;
import com.example.licensedtennismatches.model.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<UUID, Customer> customers = new HashMap<>();

    @Override
    public void store(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<Customer> findById(UUID customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }
}
