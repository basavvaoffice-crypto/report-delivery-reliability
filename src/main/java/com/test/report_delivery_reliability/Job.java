package com.test.report_delivery_reliability;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {
    @JsonProperty("job_id")
    private String jobId;
    @JsonProperty("run_id")
    private String runId;
    @JsonProperty("tenant_id")
    private String tenantId;
    @JsonProperty("tenant_name")
    private String tenantName;
    @JsonProperty("report_name")
    private String reportName;
    private String status;
    @JsonProperty("attempt_number")
    private int attemptNumber;

    @JsonProperty("scheduled_at")
    private String scheduledAt;

    @JsonProperty("duration_seconds")
    private int durationSeconds;

    @JsonProperty("row_count")
    private Integer rowCount;

    // Getters and Setters
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(int attemptNumber) {
        this.attemptNumber = attemptNumber;
    }
}