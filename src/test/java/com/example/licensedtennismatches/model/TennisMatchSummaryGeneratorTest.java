package com.example.licensedtennismatches.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TennisMatchSummaryGeneratorTest {

    private TennisMatch match;

    @BeforeEach
    public void setup() {
        match = new TennisMatch(UUID.randomUUID(), "a", Date.from(Instant.now()), "b", "c");
    }

    @Test
    public void initiallyEmpty() {
        assertThat(match.getSummary()).isEmpty();
    }

    @Test
    public void acceptsGeneratedSummaries() {
        match.accept(match -> "foo");

        assertThat(match.getSummary()).containsExactly("foo");
    }

    @Test
    public void removesDuplicatedSummaries() {
        match.accept(match -> "foo");
        match.accept(match -> "foo");
        match.accept(match -> "foo");

        assertThat(match.getSummary()).containsExactly("foo");
    }

    @Test
    public void acceptsMultipleSummaries() {
        match.accept(match -> "foo");
        match.accept(match -> "bar");
        match.accept(match -> "bar");
        match.accept(match -> "foo");

        assertThat(match.getSummary()).containsExactlyInAnyOrder("foo", "bar");
    }
}