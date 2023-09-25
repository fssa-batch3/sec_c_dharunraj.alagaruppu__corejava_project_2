package com.fssa.netbliz.model;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.fssa.netbliz.dao.UpdateAverageBalanceJob;

public class CronJob {

	public static void main(String[] args) throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // Define the JobDetail
        JobDetail jobDetail = JobBuilder.newJob(UpdateAverageBalanceJob.class)
                .withIdentity("updateAverageJob") 
                .build();

        // Define the Trigger with the cron expression (11:59 PM)
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dailyUpdateTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 56 20 * * ?"))
                .build();


        // Schedule the Job with the Trigger
        scheduler.scheduleJob(jobDetail, trigger);

        // Start the Scheduler
        scheduler.start();
    }
}

