package com.example.licensedtennismatches.model;

import java.util.List;
import java.util.UUID;

public interface TennisRepository {

    default UUID nextId() {
        return UUID.randomUUID();
    }

    void store(TennisMatch match);

    List<TennisMatch> findAll();

    List<TennisMatch> findByCustomer(Customer customer);
}
