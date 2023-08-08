package com.fssa.netbliz.dao;

import java.security.SecureRandom;

public class AccountBalanceCreater {
	private SecureRandom random = new SecureRandom();

	public double randomBalance() {
		double min = 500.0;
		double max = 20000.0;

		double randomNumber = this.random.nextDouble(max - min) + min;

		return randomNumber;

	}
}
