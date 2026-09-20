package com.assignment.ledger.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(
        BigDecimal amount,
        String currency
) {

    public Money {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be empty");
        }

        amount = amount.setScale(getScale(currency), RoundingMode.HALF_UP);
    }

    private static int getScale(String currency) {
        return switch (currency) {
            case "BHD" -> 3;
            default -> 2;
        };
    }

    public Money add(Money other) {
        validateCurrency(other);

        return new Money(
                amount.add(other.amount),
                currency
        );
    }

    public Money subtract(Money other) {
        validateCurrency(other);

        return new Money(
                amount.subtract(other.amount),
                currency
        );
    }

    public boolean isNegative() {
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isPositive() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    private void validateCurrency(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
    }
}