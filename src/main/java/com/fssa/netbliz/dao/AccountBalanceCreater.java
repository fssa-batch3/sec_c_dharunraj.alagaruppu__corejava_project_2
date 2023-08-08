package com.fssa.netbliz.dao;

import java.util.Random;

public class AccountBalanceCreater {
	private Random random = new Random();
	public  double randomBalance() {
		int min = 500;
		int max = 10000;

		double randomNumber = this.random.nextInt(max - min) + min;

//		System.out.println("Random number between 500 and 25000: " + randomNumber);
		return randomNumber;

	}
}
