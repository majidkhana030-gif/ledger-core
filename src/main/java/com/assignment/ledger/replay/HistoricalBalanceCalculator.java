package com.assignment.ledger.replay;

import com.assignment.ledger.model.Event;
import com.assignment.ledger.model.EventType;
import com.assignment.ledger.model.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class HistoricalBalanceCalculator {
    public Money calculate(
            List<Event> events,
            LocalDate date,
            String accountId,
            String currency
    ) {
        BigDecimal balance = BigDecimal.ZERO;

        for (Event event : events) {
            if (!event.accountId().equals(accountId)) {
                continue;
            }

            if (event.eventDate().isAfter(date)) {
                continue;
            }

            if (event.valueDate().isAfter(date)) {
                continue;
            }

            if (event.type() == EventType.CREDIT) {
                balance = balance.add(event.amount().amount());
            } else if (event.type() == EventType.DEBIT) {
                balance = balance.subtract(event.amount().amount());
            }
        }

        return new Money(balance, currency);
    }
}