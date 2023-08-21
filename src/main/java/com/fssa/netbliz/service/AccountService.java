package com.fssa.netbliz.service;

import java.util.List;

import com.fssa.netbliz.dao.AccountDAO;
import com.fssa.netbliz.dao.Logger;
import com.fssa.netbliz.dao.TransactionDAO;
import com.fssa.netbliz.exception.AccountDAOException;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.TransactionDAOException;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.validator.AccountValidator;

/**
 * @author DharunrajAlagaruppu
 * 
 *         This AccountServiceLayer is a class it's used to validate via
 *         validator method. It's working as a service layer of the account
 *         class.
 */

public class AccountService {
	private AccountService() {
//		private constructor
	}

	public static final int ZERO = 0;

	// Method to add an account to the database
	public static boolean addAccount(Account account) throws AccountDAOException, AccountValidatorException {

		// Validate the account using AccountValidator
		if (AccountValidator.validate(account)) {

			// If validation passes, call the AccountDao to add the account
			return AccountDAO.addAccount(account);
		}
		// If validation fails, return false
		return false;
	}

	// Method to retrieve an account by account number
	public static boolean getAccountByNumber(String accountNumber)
			throws AccountValidatorException, AccountDAOException {

		// Validate the account number using AccountValidator
		if (AccountValidator.validateAccountNumber(accountNumber)) {

			// If validation passes, call the AccountDao to get the account
			return AccountDAO.getAccountByNumber(accountNumber);
		}
		// If validation fails, return false
		return false;
	}

	// Method to check if an account exists
	public static boolean exitsCheck(Account account) throws AccountValidatorException, AccountDAOException {

		// Validate the account using AccountValidator
		if (AccountValidator.validate(account)) {

			// If validation passes, call the AccountDao to check account existence
			return AccountDAO.exitsCheck(account);
		}

		// If validation fails, return false
		return false;
	}

	// Method to check if there are inactive accounts and return true if found
	public static boolean getAllInactiveAccountNumber() throws AccountDAOException {

		// Call AccountDao to retrieve a list of inactive account numbers
		List<String> list = AccountDAO.getAllInactiveAccountNumber();

		// If the list is not empty, return true (inactive accounts exist)

		return (list.size() > ZERO);

	}

	// Method to remove an account by account number
	public static boolean removeAccountByAccountNumber(String accountNumber)
			throws AccountValidatorException, AccountDAOException {

		// Validate the account number using AccountValidator
		if (AccountValidator.validateAccountNumber(accountNumber)) {

			// If validation passes, call the AccountDao to remove the account
			return AccountDAO.removeAccountByAccountNumber(accountNumber);
		}

		// If validation fails, return false
		return false;
	}

	// Checks if an account is active
	public static boolean isActiveAccount(String accNo) throws TransactionDAOException {
		if (AccountDAO.isActiveAccount(accNo)) {
			Logger.info(accNo + " is currently active");
			return true;
		}
		return false;
	}
}
