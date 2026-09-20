# Architecture & Trade-offs

## 1. Append-only at scale

The ledger uses an append-only event model. Events are never updated or deleted.

At 100x volume, replay performance and memory usage would become the first limitation because all events are currently stored in memory.

The main unbounded state is the event history.

The cheapest improvement would be periodic snapshots. Snapshots allow balance calculation to start from a known state instead of replaying the complete history.

---

## 2. Value-dated entries in production

Value-dated transactions create operational complexity because transaction date and accounting date can differ.

In a UAE banking environment this impacts:
- Customer statements
- Interest calculation
- Regulatory reporting
- Audit tracking

Control before production:
Backdated value-date changes should require approval, reason capture and audit logging.

---

## 3. Authorization lifecycle

Authorization can end by:

- Settlement: Hold is released and final transaction is posted.
- Expiration: Hold is removed after timeout.
- Cancellation: Merchant/customer cancellation releases funds.
- Rejection: Insufficient available balance prevents authorization.

Each lifecycle change should be recorded as an auditable event.

---

## 4. What was cut and why

Database persistence:
Not included because the assessment required an in-memory implementation.

Production risk:
Data recovery and long-term storage.

API layer:
Not included because focus was ledger processing.

Production risk:
Authentication, security and external integrations.

Distributed processing:
Not included to keep event ordering deterministic.

Production risk:
Concurrency and distributed consistency.

---

## Conclusion

The design prioritizes correctness, auditability and deterministic replay.

A production system can evolve by adding persistence, snapshots and distributed event storage without changing the append-only model.