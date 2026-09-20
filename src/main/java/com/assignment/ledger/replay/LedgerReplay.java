package com.assignment.ledger.replay;

import com.assignment.ledger.model.Account;
import com.assignment.ledger.model.Event;
import com.assignment.ledger.model.Money;

import java.util.List;

public class LedgerReplay {

    private final EventProcessor eventProcessor;
    private final BalanceCalculator balanceCalculator;

    public LedgerReplay() {
        this.eventProcessor = new EventProcessor();
        this.balanceCalculator = new BalanceCalculator();
    }

    public Money replay(Account account, List<Event> events) {
        eventProcessor.process(account, events);
        return balanceCalculator.calculate(account.events(), account.currency());
    }
}
