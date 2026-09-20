package com.assignment.ledger.rules;

import com.assignment.ledger.ledger.Ledger;
import com.assignment.ledger.model.Event;
import com.assignment.ledger.model.EventType;
import com.assignment.ledger.model.Money;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyReport {
    private final Ledger ledger;

    public DailyReport(Ledger ledger) {
        this.ledger = ledger;
    }

    public void generate(String accountId, String currency, LocalDate date) {
        BigDecimal credits = BigDecimal.ZERO;
        BigDecimal debits = BigDecimal.ZERO;

        int transactionCount = 0;

        for (Event event : ledger.entries()) {

            if (!event.accountId().equals(accountId)) {
                continue;
            }

            if (!event.valueDate().equals(date)) {
                continue;
            }

            transactionCount++;

            if (event.type() == EventType.CREDIT) {
                credits = credits.add(event.amount().amount());
            }

            if (event.type() == EventType.DEBIT) {
                debits = debits.add(event.amount().amount());
            }
        }

        System.out.println("====== DAILY REPORT ======");
        System.out.println("Account: " + accountId);
        System.out.println("Date: " + date);
        System.out.println("Currency: " + currency);

        System.out.println("Credits: " + credits);
        System.out.println("Debits: " + debits);
        System.out.println("Transactions: " + transactionCount);

        System.out.println("==========================");
    }
}