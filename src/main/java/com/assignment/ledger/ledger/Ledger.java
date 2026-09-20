package com.assignment.ledger.ledger;

import com.assignment.ledger.model.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ledger {
    private final List<Event> entries = new ArrayList<>();

    public void append(Event event) {

        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }

        entries.add(event);
    }

    public List<Event> entries() {
        return Collections.unmodifiableList(entries);
    }
}
