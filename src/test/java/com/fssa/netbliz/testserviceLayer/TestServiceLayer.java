package com.fssa.netbliz.testserviceLayer;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.account.serviceLayer.AccountServiceLayer;
import com.fssa.accout.exception.validatorExceptions;
import com.fssa.netbliz.model.Account;

public class TestServiceLayer {

	Account account = new Account();

	// Creating a new valid Account object for testing.
	@Test
	public void testValidAddAccount() throws Exception {
		Account account = new Account("1232567091137458", "IDIB000K132", "9361320511", 1000.0, "savings");

		Assertions.assertTrue(AccountServiceLayer.addAccount(account));
	}

	// Specifying the account number to retrieve.
	@Test
	public void testGetAccountByNumber() throws Exception {

		String accountNumber = "1234567091123458";

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
	public void testUpdateAccount() throws SQLException, validatorExceptions {
		Account account = new Account("1234567091123458", "IDIB000K132", "9361310516", 1000.0, "savings");

		Assertions.assertTrue(AccountServiceLayer.updateAccount(account));
	}

	// Creating an Account object to check its existence.
	@Test
	public void testExitsCheck() throws SQLException, validatorExceptions {

		Account account = new Account("1234566093114458", "IDIB000K132", "9361320516", 1000.0, "savings");

		Assertions.assertTrue(AccountServiceLayer.exitsCheck(account));
	}

	// Asserting that retrieving all inactive account numbers should return true.
	@Test
	public void testGetAllInactiveAccountNumber() throws SQLException {
		Assertions.assertTrue(AccountServiceLayer.getAllInactiveAccountNumber());
	}

	// Specifying the account number to remove.
	@Test
	public void testRemoveAccountByAccountNumber() throws Exception {
		String accountNumber = "1234567891123457";
		Assertions.assertTrue(AccountServiceLayer.removeAccountByAccountNumber(accountNumber));
	}

	@Test
	public void testGetAllActiveAccountNumber() throws SQLException {
		Assertions.assertTrue(AccountServiceLayer.getAllActiveAccountNumber());
	}
}
