# In-Memory Account Ledger Core

## Overview

In-memory event driven ledger implementation.

Features:
- Append-only ledger
- Value dated balance calculation
- Authorization and holds
- Settlement processing
- Overdraft fee handling
- Interest calculation
- Daily reporting

---

## Run Application

Requirements:
- Java 21
- Maven

Run:

```bash
mvn clean test

Run application:

Open `LedgerApplication.java` in IntelliJ and run the main method.

## Output

The application prints:

- Account closing balances
- Authorization results
- Settlement status
- Fee assessment
- Interest calculation
- Daily reports

## Design Notes

The ledger follows an append-only event model.

Events are never modified or deleted.

Corrections are represented using new events such as reversals.

Balances are calculated using value dates to maintain accounting accuracy.

## Project Structure
src/main/java/com/assignment/ledger

authorization/
ledger/
model/
replay/
rules/
report/
runner/