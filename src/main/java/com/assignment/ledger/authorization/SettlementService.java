package com.assignment.ledger.authorization;

import com.assignment.ledger.model.Event;
import com.assignment.ledger.model.EventType;
import com.assignment.ledger.model.Hold;
import com.assignment.ledger.model.Money;
import java.time.LocalDate;

public class SettlementService {
    private final HoldService holdService;

    public SettlementService(HoldService holdService) {
        this.holdService = holdService;
    }

    public Event settle(
            String eventId,
            String accountId,
            Hold hold,
            Money settlementAmount,
            LocalDate settlementDate,
            LocalDate valueDate
    ) {
        if (!hold.active()) {
            throw new IllegalStateException("Hold already released");
        }

        holdService.releaseHold(hold.holdId());

        return new Event(
                eventId,
                EventType.DEBIT,
                accountId,
                settlementAmount,
                settlementDate,
                valueDate,
                "Settlement for " + hold.holdId()
        );
    }
}