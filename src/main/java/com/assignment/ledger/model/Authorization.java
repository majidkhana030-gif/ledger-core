package com.assignment.ledger.model;

import java.time.LocalDateTime;

public record Authorization(
        String authorizationId,
        String accountId,
        Money amount,
        boolean approved,
        String reason,
        LocalDateTime createdAt
) {

    public Authorization {

        if (authorizationId == null || authorizationId.isBlank()) {
            throw new IllegalArgumentException(
                    "Authorization id cannot be empty"
            );
        }

        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException(
                    "Account id cannot be empty"
            );
        }

        if (amount == null) {
            throw new IllegalArgumentException(
                    "Amount cannot be null"
            );
        }
    }
}