package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.dao.Logger;
import com.fssa.netbliz.exception.AccountDAOException;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.TransactionDAOException;
import com.fssa.netbliz.model.Account;

public class TestAccountService {

	Account account = new Account();

	@Test
	/**
	 * This method tests the addition of a valid Account object to the system. It
	 * creates a new Account instance with the provided details and asserts that the
	 * addition process is successful.
	 *
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 */ 

	public void testValidAddAccount() throws AccountDAOException, AccountValidatorException { // before commit update
																								// the new account
																								// number

		Account account = new Account("0987654321123454", "IDIB000K132", "9600678232", 5000.0, "savings");

		Assertions.assertTrue(AccountService.addAccount(account));

		Logger.info("Account Added Successfully");
	}

	@Test

	/**
	 * This method tests the retrieval of an Account by specifying its account
	 * number. It asserts that the retrieval process is successful for a valid
	 * account number.
	 *
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 */

	public void testGetAccountByNumber() throws AccountDAOException, AccountValidatorException {

		String accountNumber = "1234567890123456";

		Assertions.assertTrue(AccountService.getAccountByNumber(accountNumber));
	}

	@Test

	/**
	 * This method tests the retrieval failure when an invalid account number is
	 * specified. It asserts that the retrieval process fails for an invalid account
	 * number.
	 *
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 */

	public void testInvalidGetAccountByNumber() throws AccountDAOException, AccountValidatorException {
		String accountNumber = "1134567091123458";

		Assertions.assertTrue(AccountService.getAccountByNumber(accountNumber));
	}

	@Test

	/**
	 * This method tests the existence check of an Account object in the system. It
	 * creates an Account instance with the provided details and asserts that the
	 * existence check process is successful.
	 *
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 */

	public void testExitsCheck() throws AccountValidatorException, AccountDAOException { // before commit update
																							// the new account
																							// number
		Account account = new Account("1234567890123453", "IDIB000K132", "9361320511", 25000.0, "savings");

		Assertions.assertTrue(AccountService.exitsCheck(account));
	}

	@Test

	/**
	 * This method tests the retrieval of all inactive account numbers from the
	 * system. It asserts that the process of retrieving inactive account numbers is
	 * successful.
	 *
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 */

	public void testGetAllInactiveAccountNumber() throws AccountValidatorException, AccountDAOException {
		Assertions.assertTrue(AccountService.getAllInactiveAccountNumber());
	}

	@Test

	/**
	 * This method tests the removal of an Account by specifying its account number.
	 * It asserts that the removal process is successful for a valid account number.
	 *
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 */

	public void testRemoveAccountByAccountNumber() throws AccountValidatorException, AccountDAOException {
		String accountNumber = "8234567890123456";
		Assertions.assertTrue(AccountService.removeAccountByAccountNumber(accountNumber));
		Logger.info("Your account is removed successfully");
	}

	@Test

	/**
	 * This method tests the check for whether an account is active or not. It
	 * specifies an account number and asserts that the account is active.
	 *
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 * @throws TransactionDAOException   If there's an issue with the Transaction
	 *                                   DAO operations.
	 */

	void isActiveAccount() throws AccountDAOException, AccountValidatorException, TransactionDAOException {
		String accNo = "1234567890123456";

		// Check if the account is active and assert true if active
		Assertions.assertTrue(AccountService.isActiveAccount(accNo));
	}
}
