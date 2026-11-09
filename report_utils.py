"""
Small utility module provided as an optional starting point for the
report delivery reliability case study.

Feel free to use, modify, replace, or ignore this entirely -- it is not
guaranteed to be complete or bug-free.
"""
import json

# Statuses that represent a failed job attempt.
FAILURE_STATUSES = {"failed", "timed_out", "error"}
TRANSIENT_STATUSES = {"timed_out"}

MAX_ATTEMPTS = 3


def load_jobs(path):
    with open(path, encoding="utf-8") as f:
        data = json.load(f)
    return data.get("jobs", [])


def is_failed(job):
    return job["status"] in FAILURE_STATUSES


def is_transient_failure(job):
    return job["status"] in TRANSIENT_STATUSES


def group_by_run(jobs):
    """Group job attempts by run_id, preserving attempt order."""
    runs = {}
    for job in jobs:
        runs.setdefault(job["run_id"], []).append(job)
    for run_id in runs:
        runs[run_id].sort(key=lambda j: j["attempt_number"])
    return runs


def latest_attempt(run_jobs):
    return max(run_jobs, key=lambda j: j["attempt_number"])


def needs_retry(job, max_attempts=MAX_ATTEMPTS):
    return is_transient_failure(job) and job["attempt_number"] < max_attempts


def process_jobs(jobs, max_attempts=MAX_ATTEMPTS):
    runs = group_by_run(jobs)
    unresolved = []
    retry_log = []

    for run_id, run_jobs in runs.items():
        latest = latest_attempt(run_jobs)
        if is_failed(latest):
            if needs_retry(latest, max_attempts):
                retry_log.append(f"Retrying run_id {run_id} (attempt {latest['attempt_number'] + 1})")
            else:
                reason = "exceeded retries" if latest["attempt_number"] >= max_attempts else "permanent failure"
                unresolved.append({"run_id": run_id, "reason": reason})
    
    return unresolved, retry_log


def generate_report(unresolved, retry_log):
    print("Unresolved Runs:")
    for entry in unresolved:
        print(f"Run ID: {entry['run_id']}, Reason: {entry['reason']}")
    print("\nRetry Log:")
    for log in retry_log:
        print(log)


if __name__ == "__main__":
    jobs = load_jobs("starter_kit/data/report_jobs.json")
    unresolved, retry_log = process_jobs(jobs)
    generate_report(unresolved, retry_log)
