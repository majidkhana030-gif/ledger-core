package com.assignment.ledger.ledger;

import com.assignment.ledger.model.Money;
import com.assignment.ledger.replay.BalanceCalculator;
import java.time.LocalDate;

public class LedgerBalanceService {
    private final BalanceCalculator calculator;

    public LedgerBalanceService() {
        this.calculator = new BalanceCalculator();
    }

    public Money balanceAt(
            Ledger ledger,
            LocalDate date,
            String accountId,
            String currency
    ) {
        return calculator.calculate(ledger.entries(), date, accountId, currency);
    }
}
