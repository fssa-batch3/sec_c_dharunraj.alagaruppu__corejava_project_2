package com.fssa.netbliz.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.enums.AccountEnum;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.util.Logger;

public class TestAccountService {

	Account account = new Account();

	AccountService accountService = new AccountService();  
 
	/**
	 * This method tests the addition of a new account using the provided account
	 * details.
	 *
	 * @throws ServiceException If an error occurs while attempting to add the new
	 *                          account.
	 */

	@Test
	// Valid test case
	public void testAddAccount() { // before commit update the new account number

		Account account = new Account("0987654321012341", "IDIB000K132", 7402473347l, 2500.0, AccountEnum.SAVINGS);

		try {
			Assertions.assertTrue(accountService.addAccount(account));
		} catch (ServiceException e) {

			Assertions.fail(e);
		}
	}

	/**
	 * This method tests the addition of an account with invalid parameters.
	 * 
	 * @throws ServiceException If an unexpected error occurs while attempting to
	 *                          add the invalid account.
	 */

	@Test
	public void testInvalidAddAccount() {

		Account account = new Account("1234567890123456", "IDIB000K132", 9361320511l, 500.0, AccountEnum.SAVINGS);

		try {
			Assertions.assertFalse(accountService.addAccount(account));
		} catch (ServiceException e) {

			e.printStackTrace();
		}

	} 

	/**
	 * This method tests the occurrence of a validation error during the addition of
	 * an account.
	 *
	 * @throws ServiceException If the expected validation error does not occur
	 *                          while attempting to add the account.
	 */

	@Test
	public void testValidationErrorAddAccount() {
		Account account = new Account("1234567890", "IDIB000K132", 9361320511l, 500.0, AccountEnum.SAVINGS);

		assertThrows(ServiceException.class, () -> accountService.addAccount(account));

	}

	/**
	 * This method tests the handling of a DAO error during the addition of an
	 * account.
	 *
	 * @throws ServiceException If the expected DAO error is not encountered while
	 *                          attempting to add the account.
	 */

	@Test
	public void testDAOErrorAddAccount() {
		Account account = new Account("09876564321123451", "IDIB000K132", 9360067834l, 500.0, AccountEnum.SAVINGS);

		assertThrows(ServiceException.class, () -> accountService.addAccount(account));

	}

	/**
	 * This method tests the removal of an Account by specifying its account number.
	 * It asserts that the removal process is successful for a valid account number.
	 *
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 */
	@Test
	// Valid test case
	public void testRemoveAccountByAccountNumber() {
		String accountNumber = "1234567890123455";
		try {
			Assertions.assertTrue(accountService.removeAccountByAccountNumber(accountNumber));

		} catch (ServiceException e) {

			Assertions.fail("Remove account is failed");

		}

	}

	/**
	 * This method tests the removal of an account using an invalid account number.
	 *
	 * @throws ServiceException If the unexpected removal of an account occurs while
	 *                          using an invalid account number.
	 */

	@Test
	public void testInvalidRemoveAccountByAccountNumber() {

		String accountNumber = "0987654321123451";

		try {
			Assertions.assertFalse(accountService.removeAccountByAccountNumber(accountNumber));
		} catch (ServiceException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method tests the occurrence of a validation error during the removal of
	 * an account.
	 * 
	 * @throws ServiceException If the expected validation error does not occur
	 *                          while attempting to remove the account.
	 */

	@Test
	public void testValidationErrorRemoveAccount() {
		String accountNumber = "12345671";

		assertThrows(ServiceException.class, () -> accountService.removeAccountByAccountNumber(accountNumber));

	}

	@Test
	// Valid test case
	public void testAddExistsAccount() { // before commit update the new account number

		Account account = new Account("0997654321023452", "IDIB000K132", 7402473347l, 2500.0, AccountEnum.SAVINGS);

		try {
			Assertions.assertTrue(accountService.addAccount(account));
		} catch (ServiceException e) {

			Assertions.fail(e);
		}
	}

	@Test
	// Valid test case
	public void testGetAccountByPhoneNumber() {
 
		long phoneNumber = 9361320511l; 
		try {

			List<Account> list = (accountService.getAccountByPhoneNumber(phoneNumber));

			for (Account ac : list) {

				Logger.info(ac);

			}

			Assertions.assertTrue(!list.isEmpty());

		} catch (ServiceException e) {

			Assertions.fail("Account details can't display");

		}
	}

	/**
	 * Test case for retrieving accounts by account number using the
	 * accountService's `getAccount` method. It verifies whether accounts associated
	 * with a specific account number can be successfully retrieved.
	 *
	 * @throws ServiceException If there is an issue with the service operation
	 *                          during the test.
	 */
	@Test
	// Valid test case
	public void testGetAccountByNumber() {

		String accountNumber = "1234567890123456";
		try {

			List<Account> list = (accountService.getAccountByNumber(accountNumber));

			for (Account ac : list) {

				Logger.info(ac);

			}

			Assertions.assertTrue(!list.isEmpty());

		} catch (ServiceException e) {

			Assertions.fail("Account details can't display the data");

		}
	}

	/**
	 * Test case to verify the behavior of the `getAccountByNumber` method in the
	 * context of an invalid account number.
	 */

	@Test
	public void testInValidGetAccountByNumber() {

		String accountNumber = "1234562890223456";

		try {
			Assertions.assertNull(accountService.getAccountByNumber(accountNumber));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

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
	@Test
	// Valid test case
	void isActiveAccount() {
		String accNo = "1234567890123456";

		try {
			Assertions.assertTrue(accountService.isActiveAccount(accNo));
		} catch (ServiceException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method tests the determination of an invalid account's activity status.
	 * 
	 * @throws ServiceException If the activity status determination unexpectedly
	 *                          indicates the account as active.
	 */

	@Test
	public void invalidIsActive() {

		String accNo = "123456789009876";

		try {
			Assertions.assertFalse(accountService.isActiveAccount(accNo));
		} catch (ServiceException e) {

			e.printStackTrace();
		}

	}

	/**
	 * This method tests the occurrence of a validation error during the
	 * determination of account activity status.
	 * 
	 * @throws ServiceException If the expected validation error does not result in
	 *                          the account being considered inactive.
	 */

	@Test
	public void validationErrorIsActive() {

		String accNo = "123456909876";

		try {
			Assertions.assertFalse(accountService.isActiveAccount(accNo));
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
