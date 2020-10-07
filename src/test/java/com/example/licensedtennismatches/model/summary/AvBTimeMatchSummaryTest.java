package com.example.licensedtennismatches.model.summary;

import com.example.licensedtennismatches.model.TennisMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class AvBTimeMatchSummaryTest {

    private Instant now;
    private AvBTimeMatchSummary summary;

    @BeforeEach
    public void setup() {
        now = Instant.now();
        summary = new AvBTimeMatchSummary(() -> Date.from(now));
    }

    private void with(Supplier<Instant> instantSupplier, String expectedSuffix) {
        TennisMatch match = new TennisMatch(
            UUID.randomUUID(),
            "a",
            Date.from(instantSupplier.get()),
            "a",
            "a"
        );

        assertThat(summary.visit(match)).isEqualTo("a vs a, " + expectedSuffix);
    }

    @Test
    public void hourInTheFuture() {
        with(() -> now.plus(1, ChronoUnit.HOURS), "starts in 60 minutes");
    }

    @Test
    public void almostStarting() {
        with(() -> now.plus(2, ChronoUnit.MINUTES), "starts in 2 minutes");
    }

    @Test
    public void hourInThePast() {
        with(() -> now.minus(1, ChronoUnit.HOURS), "started 60 minutes ago");
    }
}