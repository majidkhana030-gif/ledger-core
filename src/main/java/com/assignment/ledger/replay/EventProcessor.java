package com.assignment.ledger.replay;

import com.assignment.ledger.ledger.Ledger;
import com.assignment.ledger.model.Event;
import java.util.List;

public class EventProcessor {
    public void process(Ledger ledger, List<Event> events) {
        for (Event event : events) {
            ledger.append(event);
        }
    }
}