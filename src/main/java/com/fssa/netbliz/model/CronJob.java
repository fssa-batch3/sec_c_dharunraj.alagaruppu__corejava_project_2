package com.fssa.netbliz.model;

import java.time.LocalDate;


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

	private String accountNumber;
	private double availableBalance;
	private LocalDate date;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public CronJob() {

	}

	public CronJob(String accountNumber, double availableBalance, LocalDate date) {
		this.accountNumber = accountNumber;
		this.availableBalance = availableBalance;
		this.date = date;
	}

	@Override
	public String toString() {
		return "CronJob [accountNumber=" + accountNumber + ", availableBalance=" + availableBalance + ", date=" + date
				+ "]";
	}

	public static void main(String[] args) throws Exception {
		
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		
		Scheduler scheduler = schedulerFactory.getScheduler();

		// Define the JobDetail
		JobDetail jobDetail = JobBuilder.newJob(UpdateAverageBalanceJob.class).withIdentity("updateAverageJob").build();

		// Cron expression (11:45 PM)
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dailyUpdateTrigger")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 26 23 * * ?")).build();

		// Schedule the Job with the Trigger
		scheduler.scheduleJob(jobDetail, trigger);

		// Start the Scheduler
		scheduler.start();
	}

}
