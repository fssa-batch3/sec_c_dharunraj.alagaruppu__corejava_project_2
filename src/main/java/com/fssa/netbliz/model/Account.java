package com.fssa.netbliz.model;

public class Account {

	// These are the attribute for the account class
	private String accountNumber;
	private String ifsc;
	private String phoneNumber;
	public String typeOfAccount;
	private double minimumBalance;
	// private LocalDate dateOfjoining;

	// No argument constructor
	public Account() { 
		// Empty constructor used for creating an instance without setting attributes
	}

	// Constructor to initialize account attributes with provided values
	public Account(String accountNumber, String ifsc, String phoneNumber, double minimumBalance, String typeOfAccount) {

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public String getCategory() {
		return typeOfAccount;
	}

	public void setCategory(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

}
