package com.example.licensedtennismatches.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Customer {

    private final UUID customerId;

    @JsonIgnore
    private final Set<UUID> purchasedMatches = new HashSet<>();

    @JsonIgnore
    private final Set<String> purchasedTournaments = new HashSet<>();

    public Customer(UUID customerId) {
        this.customerId = customerId;
    }

    public void purchaseMatch(UUID matchId) {
        purchasedMatches.add(matchId);
    }

    public void purchaseTournament(String tournamentName) {
        purchasedTournaments.add(tournamentName);
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public Set<UUID> getPurchasedMatches() {
        return purchasedMatches;
    }

    public Set<String> getPurchasedTournaments() {
        return purchasedTournaments;
    }

    public boolean hasPurchased(TennisMatch match) {
        return purchasedMatches.contains(match.getMatchId())
            || purchasedTournaments.contains(match.getTournamentName());
    }
}
