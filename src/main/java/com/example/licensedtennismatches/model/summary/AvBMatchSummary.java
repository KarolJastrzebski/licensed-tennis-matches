package com.example.licensedtennismatches.model.summary;

import com.example.licensedtennismatches.model.MatchSummary;
import com.example.licensedtennismatches.model.TennisMatch;
import org.springframework.stereotype.Component;

@Component
public class AvBMatchSummary implements MatchSummary {

    @Override
    public String visit(TennisMatch match) {
        return match.getPlayerA() + " vs " + match.getPlayerB();
    }
}
