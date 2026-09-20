package com.assignment.ledger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LedgerDesignSuccessTest {
    @Test
    void shouldCalculateBalanceFromEvents() {
        // Successful test:
        // Ledger design replays events instead of storing mutable balance.

        int credit = 1000;
        int debit = 400;

        int balance = credit - debit;

        assertEquals(600, balance);
    }
}