package com.fssa.netbliz.service;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.netbliz.enums.AccountEnum;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.util.Logger;

public class TestAccountService {

	Account account = new Account();

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

	public void testAddAccount() throws ServiceException { // before commit update
															// the new account
															// number
		Account account = new Account("1234567890123457", "IDIB000K132", 9361320511l, 500.0, AccountEnum.SAVINGS);

		try {
			Assertions.assertTrue(AccountService.exitsCheck(account));
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
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

	public void testRemoveAccountByAccountNumber() throws ServiceException {
		String accountNumber = "0987654321123454";
		try {
			Assertions.assertTrue(AccountService.removeAccountByAccountNumber(accountNumber));
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
		Logger.info("Your account is removed successfully");
	}

	@Test

	/**
	 * Test case for retrieving accounts by account number using the
	 * AccountService's `getAccount` method. It verifies whether accounts associated
	 * with a specific account number can be successfully retrieved.
	 *
	 * @throws ServiceException If there is an issue with the service operation
	 *                          during the test.
	 */

	public void testGetAccountByNumber() throws ServiceException {

		try {

			List<Account> accountList = AccountService.getAccount("1234567890123456");

			for (Account ac : accountList) {

				Logger.info(ac);
			}
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
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

	public void testGetAllInactiveAccountNumber() throws ServiceException {
		try {
			Assertions.assertTrue(AccountService.getAllInactiveAccountNumber());
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
	}

	@Test

	/**
	 * Test case for checking the reactivation of an Account using the
	 * AccountService's `exitsCheck` method. It verifies whether an Account can be
	 * reactivated or not based on its existence.
	 *
	 * @throws ServiceException If there is an issue with the service operation
	 *                          during the test.
	 */

	public void testReactivateAccount() throws ServiceException {

		Account account = new Account("0987654321123451", "IDIB000K132", 9600678232l, 5000.0, AccountEnum.SAVINGS);

		try {
			Assertions.assertTrue(AccountService.exitsCheck(account));
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
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

	void isActiveAccount() throws ServiceException {
		String accNo = "1234567890123456";

		// Check if the account is active and assert true if active
		try {
			Assertions.assertTrue(AccountService.isActiveAccount(accNo));
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
	}

}
