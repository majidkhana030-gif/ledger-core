package com.assignment.ledger.authorization;

import com.assignment.ledger.model.Hold;
import com.assignment.ledger.model.Money;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoldService {
    private final List<Hold> holds = new ArrayList<>();

    public Hold createHold(String holdId, String accountId, Money amount, LocalDate createdDate) {
        Hold hold = new Hold(holdId, accountId, amount, createdDate, true);

        holds.add(hold);
        return hold;
    }

    public void releaseHold(String holdId) {
        for (int i = 0; i < holds.size(); i++) {
            Hold hold = holds.get(i);
            if (hold.holdId().equals(holdId)) {
                holds.set(i, hold.release());
                return;
            }
        }
    }

    public List<Hold> getActiveHolds() {
        return holds.stream()
                .filter(Hold::active)
                .toList();
    }

    public Hold findHold(String holdId) {
        return holds.stream()
                .filter(hold -> hold.holdId().equals(holdId))
                .findFirst()
                .orElse(null);
    }
}
