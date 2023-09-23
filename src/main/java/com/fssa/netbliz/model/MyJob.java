package com.fssa.netbliz.model;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Your task logic goes here
        System.out.println("Task executed at 11:59 PM.");
    }
}
