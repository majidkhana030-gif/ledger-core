package com.assignment.ledger.authorization;

import com.assignment.ledger.model.Account;
import com.assignment.ledger.model.Authorization;
import com.assignment.ledger.model.Hold;
import com.assignment.ledger.model.Money;
import java.time.LocalDateTime;
import java.util.List;

public class AuthorizationService {
    private final AvailableBalanceCalculator balanceCalculator;
    public AuthorizationService(AvailableBalanceCalculator balanceCalculator) {
        this.balanceCalculator = balanceCalculator;
    }

    public Authorization authorize(
            Account account,
            String authorizationId,
            Money ledgerBalance,
            Money amount,
            List<Hold> activeHolds
    ) {
        Money availableBalance = balanceCalculator.calculate(ledgerBalance, activeHolds);
        boolean approved = availableBalance.amount()
                        .subtract(amount.amount())
                        .compareTo(java.math.BigDecimal.ZERO) >= 0;

        return new Authorization(
                authorizationId,
                account.accountId(),
                amount,
                approved,
                approved ? "Approved" : "Insufficient available balance",
                LocalDateTime.now()
        );
    }
}