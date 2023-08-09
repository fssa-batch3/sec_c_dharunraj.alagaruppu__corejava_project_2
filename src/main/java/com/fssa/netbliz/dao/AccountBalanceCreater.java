package com.fssa.netbliz.dao;

import java.security.SecureRandom;

public class AccountBalanceCreater {
	
	public static final double FROM_MINIMUM_BALANCE = 500.0;
	
	public static final double UPTO_MINIMUM_BALANCE = 20000.0;
	
	
	private SecureRandom random = new SecureRandom();

	public double randomBalance() {
		double min = FROM_MINIMUM_BALANCE; 
		double max = UPTO_MINIMUM_BALANCE;

		double randomNumber = this.random.nextDouble(max - min) + min;

		return randomNumber;
 
	}
}
