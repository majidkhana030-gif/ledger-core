package com.assignment.ledger.runner;

import com.assignment.ledger.authorization.AuthorizationService;
import com.assignment.ledger.authorization.AvailableBalanceCalculator;
import com.assignment.ledger.authorization.HoldService;
import com.assignment.ledger.authorization.SettlementService;
import com.assignment.ledger.ledger.Ledger;
import com.assignment.ledger.ledger.LedgerBalanceService;
import com.assignment.ledger.model.*;
import com.assignment.ledger.replay.EventProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LedgerApplication {

    private static final LocalDate DAY1 = LocalDate.of(2026, 9, 20);
    private static final LocalDate DAY2 = LocalDate.of(2026, 9, 21);
    private static final LocalDate DAY4 = LocalDate.of(2026, 9, 23);
    private static final LocalDate DAY5 = LocalDate.of(2026, 9, 24);
    private static final LocalDate DAY6 = LocalDate.of(2026, 9, 25);


    public static void main(String[] args) {

        Account account = new Account("ACC-001", "AED");

        Account account2 = new Account("ACC-002", "BHD");

        Ledger ledger = new Ledger();

        EventProcessor eventProcessor = new EventProcessor();

        List<Event> events = List.of(new Event("E1", EventType.CREDIT, "ACC-001", new Money(
                                new BigDecimal("1200.00"),
                                "AED"), DAY1, DAY1, "Credit"),

                new Event("E2", EventType.DEBIT, "ACC-001",
                        new Money(
                                new BigDecimal("950.00"),
                                "AED"
                        ),
                        DAY1,
                        DAY1,
                        "Debit"
                )
        );

        eventProcessor.process(ledger, events);

        LedgerBalanceService balanceService = new LedgerBalanceService();

        Money balance = balanceService.balanceAt(ledger, DAY1, "ACC-001", "AED");

        System.out.println("Closing Balance: " + balance.amount() + " " + balance.currency());

        HoldService holdService = new HoldService();

        SettlementService settlementService = new SettlementService(holdService);

        AuthorizationService authorizationService = new AuthorizationService(new AvailableBalanceCalculator());

        // E3 Authorization
        Authorization authorization = authorizationService.authorize(
                        account,
                        "Auth-A",
                        balance,
                        new Money(
                                new BigDecimal("200.00"),
                                "AED"
                        ),
                        holdService.getActiveHolds()
                );

        System.out.println("Authorization: "
                        + authorization.authorizationId()
                        + " "
                        + (authorization.approved()
                        ? "APPROVED"
                        : "REJECTED")
        );

        Hold authAHold = null;

        if (authorization.approved()) {
            authAHold = holdService.createHold(
                            authorization.authorizationId(),
                            account.accountId(),
                            authorization.amount(),
                            DAY2
                    );

            System.out.println("Hold Created: "
                            + authAHold.holdId()
                            + " Amount: "
                            + authAHold.amount().amount()
            );
        }

        // E5 Settlement
        if (authAHold != null) {
            Event settlementEvent = settlementService.settle("E5", account.accountId(), authAHold,
                            new Money(
                                    new BigDecimal("185.00"),
                                    "AED"
                            ),
                            DAY4,
                            DAY4
                    );

            ledger.append(settlementEvent);

            System.out.println("Settlement Created: "
                            + settlementEvent.eventId()
                            + " Amount: "
                            + settlementEvent.amount().amount()
            );
        }

        // E6 Invalid Settlement
        Hold authZHold = holdService.findHold("Auth-Z");

        if (authZHold == null) {
            System.out.println("Settlement Error: Auth-Z authorization not found");
        }

        // E7 Backdated Debit
        Event e7 = new Event(
                        "E7",
                        EventType.DEBIT,
                        "ACC-001",
                        new Money(
                                new BigDecimal("620.00"),
                                "AED"
                        ),
                        DAY5,
                        DAY2,
                        "Backdated debit"
                );

        ledger.append(e7);

        Money day2Balance = balanceService.balanceAt(
                        ledger,
                        DAY2,
                        "ACC-001",
                        "AED"
                );

        System.out.println("Day 2 Closing Balance: " + day2Balance.amount() + " AED");

        // E8 Auth-B
        Money currentBalance = balanceService.balanceAt(
                        ledger,
                        DAY5,
                        "ACC-001",
                        "AED"
                );

        Authorization authB = authorizationService.authorize(
                        account,
                        "Auth-B",
                        currentBalance,
                        new Money(
                                new BigDecimal("90.00"),
                                "AED"
                        ),
                        holdService.getActiveHolds()
                );

        System.out.println("Authorization: "
                        + authB.authorizationId()
                        + " "
                        + (authB.approved()
                        ? "APPROVED"
                        : "REJECTED")
        );

        // E9 Reversal
        Event e9 = new Event(
                        "E9",
                        EventType.CREDIT,
                        "ACC-001",
                        new Money(
                                new BigDecimal("620.00"),
                                "AED"
                        ),
                        DAY6,
                        DAY2,
                        "Reversal of E7"
                );

        ledger.append(e9);

        Money afterReversal = balanceService.balanceAt(
                        ledger,
                        DAY2,
                        "ACC-001",
                        "AED"
                );

        System.out.println("Day 2 Balance After E9: "
                        + afterReversal.amount()
                        + " AED"
        );

        // E10 BHD instalments
        Event e10a = new Event(
                        "E10-1",
                        EventType.CREDIT,
                        "ACC-002",
                        new Money(
                                new BigDecimal("3.333"),
                                "BHD"
                        ),
                        DAY5,
                        DAY5,
                        "BHD instalment 1"
                );

        Event e10b = new Event(
                        "E10-2",
                        EventType.CREDIT,
                        "ACC-002",
                        new Money(
                                new BigDecimal("3.333"),
                                "BHD"
                        ),
                        DAY5,
                        DAY5,
                        "BHD instalment 2"
                );

        Event e10c = new Event(
                        "E10-3",
                        new BigDecimal("3.334")
                                .compareTo(BigDecimal.ZERO) > 0
                                ? EventType.CREDIT
                                : EventType.DEBIT,
                        "ACC-002",
                        new Money(
                                new BigDecimal("3.334"),
                                "BHD"
                        ),
                        DAY5,
                        DAY5,
                        "BHD instalment 3"
                );

        ledger.append(e10a);
        ledger.append(e10b);
        ledger.append(e10c);

        Money bhdBalance = balanceService.balanceAt(
                        ledger,
                        DAY5,
                        "ACC-002",
                        "BHD"
                );

        System.out.println("ACC-002 Balance: "
                        + bhdBalance.amount()
                        + " "
                        + bhdBalance.currency()
        );
    }
}