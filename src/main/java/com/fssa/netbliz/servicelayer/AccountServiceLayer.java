package com.fssa.netbliz.servicelayer;

import java.util.List;

import com.fssa.netbliz.dao.AccountDao;
import com.fssa.netbliz.exception.AccountValidatorExceptions;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.validator.AccountValidator;

/*
 * This AccountServiceLayer is a class it's used to validate via validator method. It's working as a service layer of the account class.
 */

public class AccountServiceLayer {
	public static final int ZERO = 0;

	// Method to add an account to the database
	public static boolean addAccount(Account account) throws AccountValidatorExceptions {

		// Validate the account using AccountValidator
		if (AccountValidator.validate(account)) {

			// If validation passes, call the AccountDao to add the account
			return AccountDao.addAccount(account);
		}
		// If validation fails, return false
		return false;
	}

	// Method to retrieve an account by account number
	public static boolean getAccountByNumber(String accountNumber) throws AccountValidatorExceptions {

		// Validate the account number using AccountValidator
		if (AccountValidator.validateAccountNumber(accountNumber)) {

			// If validation passes, call the AccountDao to get the account
			return AccountDao.getAccountByNumber(accountNumber);
		}
		// If validation fails, return false
		return false;
	}

	// Method to update an account
	public static boolean updateAccount(Account account) throws AccountValidatorExceptions {

		// Validate the account using AccountValidator
		if (AccountValidator.validate(account)) {

			// If validation passes, call the AccountDao to update the account
			return AccountDao.updateAccount(account);
		}

		// If validation fails, return false
		return false;
	}

	// Method to check if an account exists
	public static boolean exitsCheck(Account account) throws AccountValidatorExceptions {

		// Validate the account using AccountValidator
		if (AccountValidator.validate(account)) {

			// If validation passes, call the AccountDao to check account existence
			return AccountDao.exitsCheck(account);
		}

		// If validation fails, return false
		return false;
	}

	// Method to check if there are inactive accounts and return true if found
	public static boolean getAllInactiveAccountNumber() throws AccountValidatorExceptions {

		// Call AccountDao to retrieve a list of inactive account numbers
		List<String> list = AccountDao.getAllInactiveAccountNumber();

		// If the list is not empty, return true (inactive accounts exist)
		if (list.size() > ZERO) {
			return true;
		}

		// If the list is empty, return false (no inactive accounts)
		return false;
	}

	// Method to remove an account by account number
	public static boolean removeAccountByAccountNumber(String accountNumber) throws AccountValidatorExceptions {

		// Validate the account number using AccountValidator
		if (AccountValidator.validateAccountNumber(accountNumber)) {

			// If validation passes, call the AccountDao to remove the account
			return AccountDao.removeAccountByAccountNumber(accountNumber);
		}

		// If validation fails, return false
		return false;
	}

}
