# Rejected Criteria

## 1. Day 2 Closing Balance

Rejected:
"Day 2 closing balance is AED -370.00 before fee assessment."

Reason:
After applying correction events with the same value date, the final balance is restored.

---

## 2. After E9 All Fees Return Automatically

Rejected:
"All balances and fees return to pre-E7 values."

Reason:
Business corrections do not automatically reverse unrelated fees unless an explicit reversal event exists.

---

## 3. BHD Instalments

Rejected:
"All three BHD instalments must be 3.334."

Reason:
This creates 10.002 BHD instead of the required 10.000 BHD.

---

## 4. Interest Remainder Discard

Rejected:
"Interest rounding remainder should be discarded."

Reason:
Financial systems should preserve calculated value and avoid unexplained loss.

---

## Abandoned Approaches

- Mutable balance storage was rejected because it reduces auditability.
- Updating existing events was rejected because ledger history must remain immutable.