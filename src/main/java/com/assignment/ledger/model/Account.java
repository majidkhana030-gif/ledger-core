package com.assignment.ledger.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private final String accountId;
    private final String currency;

    private final List<Event> events = new ArrayList<>();

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

    public void addEvent(Event event) {

        if (!event.accountId().equals(accountId)) {
            throw new IllegalArgumentException(
                    "Event does not belong to this account"
            );
        }
        events.add(event);
    }

    public String accountId() {
        return accountId;
    }

    public String currency() {
        return currency;
    }

    public List<Event> events() {
        return Collections.unmodifiableList(events);
    }
}