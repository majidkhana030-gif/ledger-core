package com.assignment.ledger.report;

public class ReportPrinter {


    public void print(DailyReport report) {
        System.out.println("========== Day " + report.day() + " ==========");
        System.out.println("Closing Balance: " + report.closingBalance().amount() + " "
                        + report.closingBalance().currency()
        );

        System.out.println("Fees:");

        if (report.feeAssessments().isEmpty()) {
            System.out.println("  None");
        } else {
            report.feeAssessments().forEach(fee -> System.out.println("  " + fee));
        }

        System.out.println("Authorization States:");

        if (report.authorizationStates().isEmpty()) {
            System.out.println("  None");
        } else {
            report.authorizationStates()
                    .forEach(auth -> System.out.println("  " + auth));
        }

        System.out.println("Errors:");

        if (report.errors().isEmpty()) {
            System.out.println("  None");
        } else {
            report.errors()
                    .forEach(error -> System.out.println("  " + error));
        }
        System.out.println();
    }
}