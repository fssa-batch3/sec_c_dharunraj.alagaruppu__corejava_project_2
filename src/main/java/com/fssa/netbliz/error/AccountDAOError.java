package com.fssa.netbliz.error;

public final class AccountDAOError {

    // account update is invalid
	public static final String INVALID_UPDATE = "Can't update the account";

    // account already exists
    public static final String ERROR_ALREADY_EXITS = "The account is already exists";
    
    public static final String NO_INVALID_ACCOUNT = "No invalid account occour";
    
    public static final String INVALID_ACCOUNT_NUMBER = "Account number or IFSC code is not valid";
    
    public static final String INVALID_PHONE_NUMBER = "This phone number have account in netbliz .Please use that account ";
    
    private AccountDAOError(){
    	
    }

}