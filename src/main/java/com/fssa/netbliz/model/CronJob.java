package com.fssa.netbliz.model;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronJob {

    public static void main(String[] args) throws Exception {
        // Create a Quartz Scheduler Factory
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // Define the Job
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob")
                .build();

        // Define a Trigger with the given cron expression
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("59 23 * * *"))
                .build();

        // Schedule the Job with the Trigger
        scheduler.scheduleJob(jobDetail, trigger);

        // Start the Scheduler
        scheduler.start();
    }
}

