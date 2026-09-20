package com.assignment.ledger.authorization;

import com.assignment.ledger.model.Hold;
import com.assignment.ledger.model.Money;

import java.math.BigDecimal;
import java.util.List;

public class AvailableBalanceCalculator {

    public Money calculate(Money ledgerBalance, List<Hold> activeHolds) {

        BigDecimal available = ledgerBalance.amount();

        for (Hold hold : activeHolds) {
            if (hold.active()) {
                available = available.subtract(hold.amount().amount());
            }
        }

        return new Money(available, ledgerBalance.currency());
    }
}