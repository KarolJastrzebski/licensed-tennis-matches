package com.example.licensedtennismatches.model.summary;

import com.example.licensedtennismatches.model.MatchSummary;
import com.example.licensedtennismatches.model.TennisMatch;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.function.Supplier;

@Component
public class AvBTimeMatchSummary implements MatchSummary {

    private static final MatchSummary A_V_B = new AvBMatchSummary();
    private final Supplier<Date> now;

    public AvBTimeMatchSummary(Supplier<Date> now) {
        this.now = now;
    }

    @Override
    public String visit(TennisMatch match) {
        Duration timeDifference = Duration.between(now.get().toInstant(), match.getStartDate().toInstant());
        String timeReference;
        if (timeDifference.isNegative()) {
            timeReference = "started " + timeDifference.abs().toMinutes() + " minutes ago";
        } else {
            timeReference = "starts in " + timeDifference.toMinutes() + " minutes";
        }
        return A_V_B.visit(match) + ", " + timeReference;
    }
}
