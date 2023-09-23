package com.fssa.netbliz.error;

public final class TransactionDAOError {

	
	public static final String INVALID_ACCOUNT_NUMBER = "Invalid receciver details";
	
	public static final String NON_TRANSACTION = "No list of account transaction is there";
	
	public static final String DATE_VALIDATOR = "Invalid date format";
	
	public static final String DISMATCH_PHONE_NUMBER = "You can't share money with your account";
	
	public static final String UN_AVAILABLE_ACCOUNT = "Your account is not available";  // isAvailableAccount
	
	public static final String WRONG_ACCOUNT_NUMBER = "You can't transfer money with your account";
	
	private TransactionDAOError() {
		
		
	}
}
