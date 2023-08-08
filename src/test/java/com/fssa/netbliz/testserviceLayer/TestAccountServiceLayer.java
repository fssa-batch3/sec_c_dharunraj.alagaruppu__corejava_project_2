package com.fssa.netbliz.testserviceLayer;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.netbliz.exception.AccountValidatorExceptions;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.serviceLayer.AccountServiceLayer;

public class TestAccountServiceLayer {

	Account account = new Account();

	// Creating a new valid Account object for testing.
	@Test
	public void testValidAddAccount() throws Exception {
		Account account = new Account("1234527890123459", "IDIB000K132", "9361320511", 1000.0, "savings");

		Assertions.assertTrue(AccountServiceLayer.addAccount(account));
	} 

	// Specifying the account number to retrieve.
	@Test
	public void testGetAccountByNumber() throws Exception {

		String accountNumber = "1234567890123456";

		Assertions.assertTrue(AccountServiceLayer.getAccountByNumber(accountNumber));
	}

	// Specifying an invalid account number to test retrieval failure.
	@Test
	public void testInvalidGetAccountByNumber() throws Exception {
		String accountNumber = "1134567091123458";

		Assertions.assertTrue(AccountServiceLayer.getAccountByNumber(accountNumber));
	}

	// Creating a new Account object with updated information.
	@Test
	public void testUpdateAccount() throws SQLException, AccountValidatorExceptions {
		Account account = new Account("1234567890123456", "IDIB000K132", "9361310511", 1000.0, "savings");

		Assertions.assertTrue(AccountServiceLayer.updateAccount(account));
	}

	// Creating an Account object to check its existence.
	@Test
	public void testExitsCheck() throws SQLException, AccountValidatorExceptions {

		Account account = new Account("3987654311123456", "IDIB000K132", "9361320516", 1000.0, "savings");

		Assertions.assertTrue(AccountServiceLayer.exitsCheck(account));
	}

	// Asserting that retrieving all inactive account numbers should return true.
	@Test
	public void testGetAllInactiveAccountNumber() throws SQLException, AccountValidatorExceptions {
		Assertions.assertTrue(AccountServiceLayer.getAllInactiveAccountNumber());
	} 

	// Specifying the account number to remove.
	@Test
	public void testRemoveAccountByAccountNumber() throws Exception {
		String accountNumber = "2345678901234561";
		Assertions.assertTrue(AccountServiceLayer.removeAccountByAccountNumber(accountNumber));
	}

	@Test
	public void testGetAllActiveAccountNumber() throws SQLException, AccountValidatorExceptions {
		Assertions.assertTrue(AccountServiceLayer.getAllActiveAccountNumber());
	}
}
