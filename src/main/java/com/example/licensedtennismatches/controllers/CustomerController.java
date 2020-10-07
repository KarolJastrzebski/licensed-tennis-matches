package com.example.licensedtennismatches.controllers;

import com.example.licensedtennismatches.model.Customer;
import com.example.licensedtennismatches.model.CustomerRepository;
import com.example.licensedtennismatches.model.MatchSummary;
import com.example.licensedtennismatches.model.TennisMatch;
import com.example.licensedtennismatches.model.TennisRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final TennisRepository tennisRepository;
    private final List<MatchSummary> summaries;

    public CustomerController(CustomerRepository customerRepository, TennisRepository tennisRepository,
        List<MatchSummary> summaries) {
        this.customerRepository = customerRepository;
        this.tennisRepository = tennisRepository;
        this.summaries = summaries;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<TennisMatch>> findPurchasedMatches(@PathVariable UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<TennisMatch> matches = tennisRepository.findByCustomer(customer);

        matches.forEach(match -> summaries.forEach(match::accept));

        return ResponseEntity.ok(matches);
    }
}
