# Ambiguities

## 1. Event Ordering

Ambiguity:
Events should be processed by insertion order or value date?

Resolution:
The ledger preserves event order, but balance calculation uses value date for accounting impact.

---

## 2. Settlement Amount Difference

Ambiguity:
Can settlement amount differ from authorization amount?

Resolution:
Allowed. Authorization represents reserved funds, while settlement represents final transaction amount.

---

## 3. Fee Timing

Ambiguity:
When should overdraft fee be assessed?

Resolution:
Fee is assessed after detecting negative balance caused by backdated transaction.

---

## 4. Reversal Handling

Ambiguity:
Should original events be modified during correction?

Resolution:
Original events remain unchanged. Corrections are represented as new events.

---

## 5. Interest Calculation

Ambiguity:
Should interest use transaction date or value date?

Resolution:
Interest uses finalized historical balance based on value date.