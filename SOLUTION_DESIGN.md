# Solution Design: Report Delivery Reliability

## Problem Restatement
Meridian Payments faces issues with scheduled financial reports occasionally failing to deliver. Failures are only noticed when customers complain. The goal is to:
1. Identify failed report runs from job history logs.
2. Automatically retry eligible failures.
3. Provide a clear view of unresolved runs and their reasons.

## Assumptions
1. **Failure Criteria**:
   - `failed`, `timed_out`, and `error` statuses indicate failure.
2. **Retry Policy**:
   - Maximum of 3 attempts per `run_id`.
   - Retries are only for transient failures (`timed_out`).
3. **Output**:
   - A report listing unresolved runs and retry actions.

## Proposed Approach
1. **Failure Detection**:
   - Extend failure detection to include `error` status.
   - Categorize failures as transient (`timed_out`) or permanent (`failed`, `error`).

2. **Retry Logic**:
   - Retry transient failures up to 3 attempts.
   - Skip retries for permanent failures.

3. **Output Report**:
   - List unresolved runs with reasons (e.g., "exceeded retries", "permanent failure").
   - Log retry actions.

4. **Scalability**:
   - Modularize logic for easy extension (e.g., adding new failure types).
   - Optimize grouping and filtering for large datasets.

## Trade-offs
1. **Retry Policy**:
   - Limiting retries to 3 balances resource usage and reliability.
   - Permanent failures are not retried to avoid unnecessary processing.
2. **Scope**:
   - Focused on identifying and retrying failures, not root cause analysis.

## Future Work
1. Implement AI-powered categorization of failure root causes.
2. Add support for real-time monitoring and alerting.
3. Optimize for larger datasets with distributed processing.

---
This document outlines the design decisions and rationale for the solution.