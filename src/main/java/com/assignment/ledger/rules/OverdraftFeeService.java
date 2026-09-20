package com.assignment.ledger.rules;

import com.assignment.ledger.ledger.Ledger;
import com.assignment.ledger.ledger.LedgerBalanceService;
import com.assignment.ledger.model.Event;
import com.assignment.ledger.model.Money;

import java.time.LocalDate;


public class OverdraftFeeService {
    private final LedgerBalanceService balanceService;
    private final FeeCalculator feeCalculator;

    public OverdraftFeeService(
            LedgerBalanceService balanceService,
            FeeCalculator feeCalculator
    ) {
        this.balanceService = balanceService;
        this.feeCalculator = feeCalculator;

    }

    public Event assess(Ledger ledger, String accountId, String currency, LocalDate day
    ) {
        Money balance =
                balanceService.balanceAt(
                        ledger,
                        day,
                        accountId,
                        currency
                );

        if (balance.amount().compareTo(
                java.math.BigDecimal.ZERO
        ) < 0) {
            return feeCalculator.assessFee(accountId, day);
        }
        return null;
    }
}
