package com.assignment.ledger.rules;

import com.assignment.ledger.model.Money;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class InterestCalculator {

    private static final BigDecimal DAILY_RATE = new BigDecimal("0.0004");

    public Money calculateDailyInterest(Money closingBalance) {
        if (closingBalance.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return new Money(BigDecimal.ZERO, closingBalance.currency());
        }

        BigDecimal interest = closingBalance.amount()
                        .multiply(DAILY_RATE)
                        .setScale(
                                getScale(closingBalance.currency()),
                                RoundingMode.HALF_UP
                        );

        return new Money(
                interest,
                closingBalance.currency()
        );
    }

    private int getScale(String currency) {
        return switch (currency) {
            case "AED" -> 2;
            case "BHD" -> 3;
            default -> throw new IllegalArgumentException(
                    "Unsupported currency"
            );
        };
    }
}
