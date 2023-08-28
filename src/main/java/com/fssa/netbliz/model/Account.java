package com.fssa.netbliz.model;

import com.fssa.netbliz.enums.AccountEnum;

public class Account {

	// These are the attribute for the account class
	private String accountNumber;
	private String ifsc;
	private long phoneNumber;
	private AccountEnum typeOfAccount;
	private double minimumBalance;

	// No argument constructor
	public Account() {     
		// Empty constructor used for creating an instance without setting attributes
	}
 
	// Constructor to initialize account attributes with provided values
	public Account(String accountNumber, String ifsc, long phoneNumber, double minimumBalance, AccountEnum  typeOfAccount) {

		this.accountNumber = accountNumber;
		this.ifsc = ifsc;
		this.phoneNumber = phoneNumber;
		this.minimumBalance = minimumBalance;
		this.typeOfAccount = typeOfAccount;
	}
 
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber; 
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public AccountEnum getCategory() {
		return typeOfAccount;
	}

	public void setCategory(AccountEnum typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", ifsc=" + ifsc + ", phoneNumber=" + phoneNumber
				+ ", typeOfAccount=" + typeOfAccount + ", minimumBalance=" + minimumBalance + "]";
	}
 

}
