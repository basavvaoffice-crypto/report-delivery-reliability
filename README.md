# Report Delivery Reliability

## Overview
This project addresses the issue of failed scheduled financial reports for Meridian Payments. It identifies failed report runs, retries eligible failures, and generates a report of unresolved runs.

## How to Run
1. **Prerequisites**:
   - Java 21+
   - Maven 3.9+

2. **Steps**:
   - Clone the repository.
   - Navigate to the project directory.
   - Run the application:
     ```bash
     mvn spring-boot:run
     ```

3. **Output**:
   - A list of failed jobs will be printed to the console.

## Known Limitations
1. Assumes `report_jobs.json` is the only input dataset.
2. Does not include real-time monitoring or alerting.
3. Limited to 3 retry attempts per run.

## Future Enhancements
1. AI-powered failure categorization.
2. Real-time monitoring and alerting.
3. Support for larger datasets with distributed processing.

---
This README provides instructions for running the solution and highlights its limitations.
