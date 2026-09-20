package com.assignment.ledger.report;

import com.assignment.ledger.model.Money;
import java.util.List;

public record DailyReport(
        int day,
        Money closingBalance,
        List<String> feeAssessments,
        List<String> authorizationStates,
        List<String> errors
) {

}