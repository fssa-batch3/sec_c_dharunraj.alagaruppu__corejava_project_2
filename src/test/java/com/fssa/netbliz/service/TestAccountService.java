package com.fssa.netbliz.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.util.Logger;

class TestAccountService {

	Account account = new Account();

	AccountService accountService = new AccountService();

	/**
	 * @throws ServiceException If an error occurs while attempting to add the new
	 *                          account.
	 */

	@Test
	// Valid test case
	void testAddAccount() { // before commit update the new account number

		Account account = new Account("1234567890123451", "IDIB000K132", 9361320511l);

		try {
			Assertions.assertTrue(accountService.getBankDetails(account));
		} catch (ServiceException e) {

			e.getMessage();
		}
	}

	/**
	 * @throws ServiceException If an unexpected error occurs while attempting to
	 *                          add the invalid account.
	 */

	@Test
	void testInvalidAddAccount() {

		Account account = new Account("1234567890123456", "IDIB000K132", 9361320511l);

		try {
			Assertions.assertFalse(accountService.addAccount(account));
		} catch (ServiceException e) {

			e.getMessage();
		}

	}

	/**
	 * @throws ServiceException If the expected validation error does not occur
	 *                          while attempting to add the account.
	 */

	@Test
	void testValidationErrorAddAccount() {
		Account account = new Account("1234567890", "IDIB000K132", 9361320511l);

		assertThrows(ServiceException.class, () -> accountService.addAccount(account));

	}

	/**
	 * @throws ServiceException If the expected DAO error is not encountered while
	 *                          attempting to add the account.
	 */

	@Test
	void testDAOErrorAddAccount() {
		Account account = new Account("09876564321123456", "IDIB000K132", 9361320511l);

		assertThrows(ServiceException.class, () -> accountService.addAccount(account));

	}

	/**
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 */
	@Test
	// Valid test case
	void testRemoveAccountByAccountNumber() {
		String accountNumber = "1234567890123455";

		try {
			Assertions.assertTrue(accountService.removeAccountByAccountNumber(accountNumber));
		} catch (ServiceException e) {

			e.getMessage();
		}

	}

	/**
	 * @throws ServiceException If the unexpected removal of an account occurs while
	 *                          using an invalid account number.
	 */

	@Test
	void testInvalidRemoveAccountByAccountNumber() {

		String accountNumber = "7820123456789012";

		try {
			Assertions.assertFalse(accountService.removeAccountByAccountNumber(accountNumber));
		} catch (ServiceException e) {

			e.getMessage();
		}
	}

	/**
	 * 
	 * @throws ServiceException If the expected validation error does not occur
	 *                          while attempting to remove the account.
	 */

	@Test
	void testValidationErrorRemoveAccount() {
		String accountNumber = "12345671";

		assertThrows(ServiceException.class, () -> accountService.removeAccountByAccountNumber(accountNumber));

	}

	@Test
	// Valid test case
	void testAddExistsAccount() { // before commit update the new account number

		Account account = new Account("1234567890123453", "IDIB000K132", 9361320511l);

		try {
			Assertions.assertTrue(accountService.addAccount(account));
		} catch (ServiceException e) {

			e.getMessage();
		}
	}

	@Test
	// Valid test case
	void testGetAccountByPhoneNumber() {

		long phoneNumber = 9361320511l;
		try {

			List<Account> list = (accountService.getAccountByPhoneNumber(phoneNumber));

			for (Account ac : list) {

				Logger.info(ac);

			}

			Assertions.assertTrue(!list.isEmpty());

		} catch (ServiceException e) {

			e.getMessage();

		}
	}

	/**
	 *
	 * @throws ServiceException If there is an issue with the service operation
	 *                          during the test.
	 */
	@Test
	// Valid test case
	void testGetAccountByNumber() {

		String accountNumber = "1234567890123456";
		try {
			
			Account acc = new Account();

			 acc = (accountService.getAccountByNumber(accountNumber));
			
			Logger.info(acc);

		} catch (ServiceException e) {

			e.getMessage();

		}
	}

	/**
	 *
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 * @throws TransactionDAOException   If there's an issue with the Transaction
	 *                                   DAO operations.
	 */
	@Test
	// Valid test case
	void isActiveAccount() {
		String accNo = "1234567890123456";

		try {
			Assertions.assertTrue(accountService.isActiveAccount(accNo));
		} catch (ServiceException e) {

			e.getMessage();
		}
	}

	/**
	 * @throws ServiceException If the activity status determination unexpectedly
	 *                          indicates the account as active.
	 */

	@Test
	void invalidIsActive() {

		String accNo = "1234567890098763";

		try {
			Assertions.assertFalse(accountService.isActiveAccount(accNo));
		} catch (ServiceException e) {

			e.getMessage();
		}

	}

	/**
	 * 
	 * @throws ServiceException If the expected validation error does not result in
	 *                          the account being considered inactive.
	 */

	@Test
	void validationErrorIsActive() {

		String accNo = "12345690987";

		Assertions.assertThrows(ServiceException.class, () -> accountService.isActiveAccount(accNo));
	}

}