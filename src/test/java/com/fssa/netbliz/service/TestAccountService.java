package com.fssa.netbliz.service;

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
	 * This method tests the retrieval of an Account by specifying its account
	 * number. It asserts that the retrieval process is successful for a valid
	 * account number.
	 *
	 * @throws AccountDAOException       If there's an issue with the Account DAO
	 *                                   operations.
	 * @throws AccountValidatorException If there's a validation error with the
	 *                                   Account details.
	 */

	public void testGetAccountByNumber() throws ServiceException {

		String accountNumber = "1234567890123456";

		try {
			Assertions.assertTrue(AccountService.getAccountByNumber(accountNumber));
			
			
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
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

	public void testInvalidGetAccountByNumber() throws ServiceException {
		String accountNumber = "1134567091123458";

		try {
			Assertions.assertTrue(AccountService.getAccountByNumber(accountNumber));
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
		Account account = new Account("0987654321123454", "IDIB000K132", "7402473346", 5000.0, AccountEnum.SAVINGS);

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
		String accountNumber = "1234567890123452";
		try {
			Assertions.assertTrue(AccountService.removeAccountByAccountNumber(accountNumber));
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
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

	void isActiveAccount() throws ServiceException {
		String accNo = "1234567890123456";

		// Check if the account is active and assert true if active
		try {
			Assertions.assertTrue(AccountService.isActiveAccount(accNo));
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
	} 

	@Test

	public void testReactivateAccount() throws ServiceException {

		Account account = new Account("1234567890123451", "IDIB000K132", "9361320511", 25000.0, AccountEnum.SAVINGS);

		try {
			Assertions.assertTrue(AccountService.exitsCheck(account));
		} catch (ServiceException e) {

			throw new ServiceException(e.getMessage());
		}
	} 
}
