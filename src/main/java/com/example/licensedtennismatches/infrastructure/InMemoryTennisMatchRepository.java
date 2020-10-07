package com.example.licensedtennismatches.infrastructure;

import com.example.licensedtennismatches.model.Customer;
import com.example.licensedtennismatches.model.TennisMatch;
import com.example.licensedtennismatches.model.TennisRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class InMemoryTennisMatchRepository implements TennisRepository {

    private final Map<UUID, TennisMatch> matches = new HashMap<>();

    @Override
    public void store(TennisMatch match) {
        matches.put(match.getMatchId(), match);
    }

    @Override
    public List<TennisMatch> findAll() {
        return new ArrayList<>(matches.values());
    }

    @Override
    public List<TennisMatch> findByCustomer(Customer customer) {
        return matches.values().stream()
            .filter(customer::hasPurchased)
            .map(TennisMatch::new)
            .collect(Collectors.toList());
    }
}
