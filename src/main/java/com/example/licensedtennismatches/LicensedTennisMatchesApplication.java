package com.example.licensedtennismatches;

import com.example.licensedtennismatches.model.Customer;
import com.example.licensedtennismatches.model.CustomerRepository;
import com.example.licensedtennismatches.model.TennisMatch;
import com.example.licensedtennismatches.model.TennisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Supplier;

@SpringBootApplication
public class LicensedTennisMatchesApplication {

    private static final Logger log = LoggerFactory.getLogger(LicensedTennisMatchesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LicensedTennisMatchesApplication.class, args);
    }

    @Bean
    public Supplier<Date> now() {
        return () -> Date.from(Instant.now());
    }

    @Bean
    public CommandLineRunner loadData(CustomerRepository customerRepository, TennisRepository tennisRepository) {
        return args -> {
            tennisRepository.store(new TennisMatch(
                tennisRepository.nextId(),
                "Grand Open",
                Date.from(Instant.now().plus(3, ChronoUnit.MINUTES)),
                "Foo",
                "Bar"
            ));
            tennisRepository.store(new TennisMatch(
                tennisRepository.nextId(),
                "Grand Open",
                Date.from(Instant.now().minus(42, ChronoUnit.MINUTES)),
                "John",
                "Peter"
            ));
            log.info("Loaded sample matches");

            Customer customer = new Customer(customerRepository.nextId());
            customer.purchaseMatch(tennisRepository.findAll().get(0).getMatchId());
            customerRepository.store(customer);
            log.info("Loaded sample customers");
        };
    }
}
