package com.assignment.ledger.rules;

import com.assignment.ledger.model.Money;
import java.time.LocalDate;

public record DailyBalanceSnapshot(
        LocalDate date,
        Money balance
) {
}