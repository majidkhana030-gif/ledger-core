package com.assignment.ledger.rules;

import com.assignment.ledger.ledger.Ledger;
import com.assignment.ledger.model.Event;
import com.assignment.ledger.model.EventType;
import com.assignment.ledger.model.Money;
import com.assignment.ledger.replay.HistoricalBalanceCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class InterestService {
    private final HistoricalBalanceCalculator historicalBalanceCalculator;
    private final InterestCalculator interestCalculator;

    public InterestService(
            HistoricalBalanceCalculator historicalBalanceCalculator,
            InterestCalculator interestCalculator
    ) {
        this.historicalBalanceCalculator = historicalBalanceCalculator;
        this.interestCalculator = interestCalculator;
    }


    public List<DailyBalanceSnapshot> createDailySnapshots(
            Ledger ledger,
            String accountId,
            String currency,
            List<LocalDate> days
    ) {

        List<DailyBalanceSnapshot> snapshots = new ArrayList<>();

        for (LocalDate day : days) {
            Money balance = historicalBalanceCalculator.calculate(
                            ledger.entries(),
                            day,
                            accountId,
                            currency
                    );

            snapshots.add(new DailyBalanceSnapshot(day, balance)
            );
        }

        return snapshots;
    }

    public Event capitalizeDay6Interest(
            Ledger ledger,
            String accountId,
            String currency,
            List<LocalDate> days,
            LocalDate capitalizationDate
    ) {

        BigDecimal totalInterest = BigDecimal.ZERO;

        // Interest is calculated on historical balances after replaying
        List<DailyBalanceSnapshot> snapshots = createDailySnapshots(
                        ledger,
                        accountId,
                        currency,
                        days
                );


        for (DailyBalanceSnapshot snapshot : snapshots) {
            System.out.println("Interest Balance "
                            + snapshot.date()
                            + ": "
                            + snapshot.balance().amount()
                            + " "
                            + currency
            );

            Money dailyInterest = interestCalculator.calculateDailyInterest(snapshot.balance());

            totalInterest = totalInterest.add(dailyInterest.amount());

            System.out.println("Interest "
                            + snapshot.date()
                            + ": "
                            + dailyInterest.amount()
                            + " "
                            + currency
            );
        }

        return new Event("INTEREST-" + UUID.randomUUID(), EventType.CREDIT, accountId,
                new Money(
                        totalInterest,
                        currency
                ),
                capitalizationDate,
                capitalizationDate,
                "Capitalized daily interest"
        );
    }
}