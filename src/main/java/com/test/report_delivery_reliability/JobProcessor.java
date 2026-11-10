package com.test.report_delivery_reliability;

import java.util.List;

public class JobProcessor {

    public void process(List<Job> jobs) {
        for (Job job : jobs) {
            if (isFailed(job)) {
                System.out.println("Failed Job: " + job.getJobId() + ", Status: " + job.getStatus());
            }
        }
    }

    private boolean isFailed(Job job) {
        return "failed".equalsIgnoreCase(job.getStatus()) || "timed_out".equalsIgnoreCase(job.getStatus()) || "error".equalsIgnoreCase(job.getStatus());
    }
}