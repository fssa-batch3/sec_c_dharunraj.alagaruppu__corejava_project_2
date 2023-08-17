package com.fssa.netbliz.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import com.fssa.netbliz.dao.Logger;
import com.fssa.netbliz.exception.AccountDaoException;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.model.Account;


public class TestAccountService {

	Account account = new Account();

	// Creating a new valid Account object for testing. 
	@Test
	public void testValidAddAccount() throws Exception { // before commit update the new account number

		Account account = new Account("2345678901234561", "IDIB000K132", "7402473347", 1000.0, "savings");

		Assertions.assertTrue(AccountService.addAccount(account));
		
		Logger.info("Account Added Successfully");
	}    

	// Specifying the account number to retrieve.
	@Test
	public void testGetAccountByNumber() throws Exception {

		String accountNumber = "1234567890123456";

		Assertions.assertTrue(AccountService.getAccountByNumber(accountNumber));
	}

	// Specifying an invalid account number to test retrieval failure.
	@Test
	public void testInvalidGetAccountByNumber() throws Exception {
		String accountNumber = "1134567091123458";

		Assertions.assertTrue(AccountService.getAccountByNumber(accountNumber));
	}

	// Creating an Account object to check its existence.
	@Test
	public void testExitsCheck() throws AccountValidatorException, AccountDaoException { // before commit update
																									// the new account
																									// number
		Account account = new Account("2345678901234452", "IDIB000K132", "7402473347", 1000.0, "savings");

		Assertions.assertTrue(AccountService.exitsCheck(account));
	}

	// Asserting that retrieving all inactive account numbers should return true.
	@Test
	public void testGetAllInactiveAccountNumber() throws AccountValidatorException, AccountDaoException  {
		Assertions.assertTrue(AccountService.getAllInactiveAccountNumber());
	}

	// Specifying the account number to remove.
	@Test
	public void testRemoveAccountByAccountNumber() throws  AccountValidatorException, AccountDaoException{
		String accountNumber = "2345678901234561";
		Assertions.assertTrue(AccountService.removeAccountByAccountNumber(accountNumber));
		Logger.info("Your account is removed successfully");
	}
}
