package com.example.licensedtennismatches.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TennisMatch {

    private final UUID matchId;
    @JsonIgnore
    private final String tournamentName;
    private final Date startDate;
    private final String playerA;
    private final String playerB;
    private final Set<String> summary = new HashSet<>();

    public TennisMatch(TennisMatch other) {
        this(other.getMatchId(), other.getTournamentName(), other.getStartDate(), other.getPlayerA(), other.getPlayerB());
    }

    public TennisMatch(UUID matchId, String tournamentName, Date startDate, String playerA, String playerB) {
        this.matchId = matchId;
        this.tournamentName = tournamentName;
        this.startDate = startDate;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getPlayerA() {
        return playerA;
    }

    public String getPlayerB() {
        return playerB;
    }

    public Set<String> getSummary() {
        return summary;
    }

    public void accept(MatchSummary generator) {
        summary.add(generator.visit(this));
    }
}
