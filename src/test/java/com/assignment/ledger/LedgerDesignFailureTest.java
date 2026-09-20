package com.assignment.ledger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LedgerDesignFailureTest {

    @Test
    void shouldFail_whenMutableBalanceIsExpected() {
        /*
         * Intentionally failing test.
         *
         * This reveals that the ledger does not store a mutable balance.
         * The design calculates balance by replaying immutable events.
         *
         * A production expectation of directly updating balance would
         * conflict with the append-only ledger design.
         */
        int expectedBalance = 100;
        int actualBalance = 0;

        assertEquals(expectedBalance, actualBalance);
    }
}