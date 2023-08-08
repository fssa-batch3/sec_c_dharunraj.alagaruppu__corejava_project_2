package com.fssa.netbliz.dao;

import java.util.Random;

public class AccountBalanceCreater {
	private Random random = new Random();
	public  double randomBalance() {
		double min = 500.0;
		double max = 20000.0; 

		double randomNumber = this.random.nextDouble(max - min) + min;

		return randomNumber;

	} 
}
