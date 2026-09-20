package com.assignment.ledger.replay;

import com.assignment.ledger.ledger.Ledger;
import com.assignment.ledger.model.Account;
import com.assignment.ledger.model.Event;
import com.assignment.ledger.model.Money;
import java.time.LocalDate;
import java.util.List;

public class LedgerReplay {
    private final EventProcessor eventProcessor;
    private final BalanceCalculator balanceCalculator;

    public LedgerReplay() {
        this.eventProcessor = new EventProcessor();
        this.balanceCalculator = new BalanceCalculator();
    }

    public Money replay(Account account, Ledger ledger, List<Event> events, LocalDate closingDate) {
        eventProcessor.process(ledger, events);

        return balanceCalculator.calculate(ledger.entries(), events.get(events.size() - 1).valueDate(), account.accountId(),
                account.currency()
        );
    }
}
