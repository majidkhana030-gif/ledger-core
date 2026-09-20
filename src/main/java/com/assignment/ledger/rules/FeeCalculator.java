package com.assignment.ledger.rules;

import com.assignment.ledger.model.Event;
import com.assignment.ledger.model.EventType;
import com.assignment.ledger.model.Money;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FeeCalculator {
    private static final BigDecimal OVERDRAFT_FEE =
            new BigDecimal("25.00");

    public Event assessFee(
            String accountId,
            LocalDate assessmentDate
    ) {
        return new Event(
                "FEE-" + UUID.randomUUID(),
                EventType.DEBIT,
                accountId,
                new Money(
                        OVERDRAFT_FEE,
                        "AED"
                ),
                assessmentDate,
                assessmentDate,
                "Overdraft fee"
        );
    }
}
