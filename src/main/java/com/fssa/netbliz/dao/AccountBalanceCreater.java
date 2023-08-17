package com.fssa.netbliz.dao;

import java.security.SecureRandom;

public class AccountBalanceCreater {

	static final double FROM_MINIMUM_BALANCE = 500.0;
	static final double UPTO_MINIMUM_BALANCE = 20000.0;
 
	private SecureRandom random = new SecureRandom();

	// Generate a random balance
	public double randomBalance() {
		double min = FROM_MINIMUM_BALANCE;
		double max = UPTO_MINIMUM_BALANCE;

		// Generate a random double value between minimum and maximum
		double randomValue = this.random.nextDouble() * (max - min) + min;

		// Round the random value to two decimal points
		return Math.round(randomValue * 100.0) / 100.0;
	}

} 