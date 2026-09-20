package com.assignment.ledger.replay;

import com.assignment.ledger.model.Account;
import com.assignment.ledger.model.Event;

import java.util.List;

public class EventProcessor {

    public void process(
            Account account,
            List<Event> events
    ) {

        for (Event event : events) {
            account.addEvent(event);
        }
    }
}