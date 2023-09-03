package com.fssa.netbliz.model;

import java.time.LocalDateTime;

import com.fssa.netbliz.enums.AccountEnum;

public class Account {

	public static final int ZERO = 0;

	public static final double CONSTANT_AVL_BALANCE = 20000.0;

	// These are the attribute for the account class
	private String accountNumber;
	private String ifsc;
	private long phoneNumber;
	private AccountEnum typeOfAccount;
	private double minimumBalance;
	private double availableBalance;
	private LocalDateTime dateOfJoining;
	private boolean isActive;
	private int customerId;

	// No argument constructor
	public Account() {
		// Empty constructor used for creating an instance without setting attributes
	}

	public Account(String accountNumber, String ifsc, long phoneNumber, double minimumBalance,
			AccountEnum typeOfAccount) {

		this.accountNumber = accountNumber;
		this.ifsc = ifsc;
		this.phoneNumber = phoneNumber;
		this.minimumBalance = minimumBalance;
		this.typeOfAccount = typeOfAccount;
	}

	public Account(String accountNumber, String ifsc, long phoneNumber, AccountEnum typeOfAccount,
			double minimumBalance, double availableBalance, LocalDateTime dateOfJoining, boolean isActive,
			int customerId) {
		super();
		this.accountNumber = accountNumber;
		this.ifsc = ifsc;
		this.phoneNumber = phoneNumber;
		this.typeOfAccount = typeOfAccount;
		this.minimumBalance = minimumBalance;
		this.availableBalance = availableBalance;
		this.dateOfJoining = dateOfJoining;
		this.isActive = isActive;
		this.customerId = customerId;
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

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public LocalDateTime getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDateTime dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", ifsc=" + ifsc + ", phoneNumber=" + phoneNumber
				+ ", typeOfAccount=" + typeOfAccount + ", minimumBalance=" + minimumBalance + ", availableBalance="
				+ availableBalance + ", dateOfJoining=" + dateOfJoining + ", isActive=" + isActive + ", customerId="
				+ customerId + "]";
	}

}
