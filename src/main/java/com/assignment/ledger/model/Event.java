package com.assignment.ledger.model;

import java.time.LocalDate;

public record Event(
        String eventId,
        EventType type,
        String accountId,
        Money amount,
        LocalDate eventDate,
        LocalDate valueDate,
        String description
) {

    public Event {
        if (eventId == null || eventId.isBlank()) {
            throw new IllegalArgumentException("Event id cannot be empty");
        }

        if (type == null) {
            throw new IllegalArgumentException("Event type cannot be null");
        }

        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("Account id cannot be empty");
        }
    }
}