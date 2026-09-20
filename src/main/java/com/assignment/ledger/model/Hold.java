package com.assignment.ledger.model;

import java.time.LocalDate;

public record Hold(
        String holdId,
        String accountId,
        Money amount,
        LocalDate createdDate,
        boolean active
) {

    public Hold {
        if (holdId == null || holdId.isBlank()) {
            throw new IllegalArgumentException("Hold id cannot be empty");
        }

        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("Account id cannot be empty");
        }

        if (amount == null) {
            throw new IllegalArgumentException("Hold amount cannot be null");
        }
    }

    public Hold release() {
        return new Hold(
                holdId,
                accountId,
                amount,
                createdDate,
                false
        );
    }
}