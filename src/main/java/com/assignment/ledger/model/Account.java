package com.assignment.ledger.model;

public class Account {
    private final String accountId;
    private final String currency;

    public Account(String accountId, String currency) {
        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("Account id cannot be empty");
        }

        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be empty");
        }

        this.accountId = accountId;
        this.currency = currency;
    }

    public String accountId() {
        return accountId;
    }

    public String currency() {
        return currency;
    }
}