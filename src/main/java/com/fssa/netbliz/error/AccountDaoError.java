package com.fssa.netbliz.error;

public final class AccountDaoError {

    // account update is invalid
	public static final String INVALID_UPDATE = "Can't update the account";

    // account already exists
    public static final String ERROR_ALREADY_EXITS = "The account is already exists";
    
    public static final String NO_INVALID_ACCOUNT = "No invalid account occour";
    
    public static final String INVALID_ACCOUNT_NUMBER = "Cann't fetch data";
    
    public static final String INVALID_PHONE_NUMBER = "This phone number have a account ";
    
    private AccountDaoError(){
    	
    }

}