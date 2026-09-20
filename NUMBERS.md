# Numbers & Constants

## Initial Balance

### E1 Credit
Value: `1200.00 AED`

Reason:
Creates initial funds to test authorization, settlement and balance calculation.

### E2 Debit
Value: `950.00 AED`

Reason:
Creates required Day 1 closing balance:

`1200 - 950 = 250 AED`

---

## Authorization & Settlement

### Auth-A Hold
Value: `200.00 AED`

Reason:
Tests successful authorization while keeping remaining available balance.

### E5 Settlement
Value: `185.00 AED`

Reason:
Demonstrates that settlement amount can be different from authorization amount.

---

## Backdated Transaction

### E7 Debit
Value: `620.00 AED`

Reason:
Creates overdraft condition:

`250 - 620 = -370 AED`

This validates overdraft fee processing.

---

## Overdraft Fee

Value: `25.00 AED`

Reason:
Fixed fee used to demonstrate fee assessment and reversal behaviour.

---

## BHD Instalments

Total: `10.000 BHD`

Distribution:

`3.333 + 3.333 + 3.334`

Reason:
BHD supports three decimal precision and the total amount must remain exactly `10.000 BHD`.

---

## Interest Rate

Daily Rate: `0.0004`

Reason:
Creates measurable daily interest accrual while keeping values realistic.

---

## Currency Precision

AED: `2 decimal places`

BHD: `3 decimal places`

Reason:
Matches currency precision rules.
