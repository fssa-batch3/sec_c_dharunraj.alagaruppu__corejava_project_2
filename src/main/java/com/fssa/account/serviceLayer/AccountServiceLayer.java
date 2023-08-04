package com.fssa.account.serviceLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.account.dao.AccountDao;
import com.fssa.accout.exception.validatorExceptions;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.validator.AccountValidator;

/*
 * This AccountServiceLayer is a class it's used to validate via validator method. It's working as a service layer of the account class.
 */

public class AccountServiceLayer { 

	// Method to add an account to the database
	public static boolean addAccount(Account account) throws SQLException, validatorExceptions {

		// Validate the account using AccountValidator
		if (AccountValidator.validate(account)) {

			// If validation passes, call the AccountDao to add the account
			return AccountDao.addAccount(account);
		}
		// If validation fails, return false
		return false;  
	}

	// Method to retrieve an account by account number
	public static boolean getAccountByNumber(String accountNumber) throws SQLException, validatorExceptions {

		// Validate the account number using AccountValidator
		if (AccountValidator.validateAccountNumber(accountNumber)) {

			// If validation passes, call the AccountDao to get the account
			return AccountDao.getAccountByNumber(accountNumber);
		}
		// If validation fails, return false
		return false;
	}

	// Method to update an account
	public static boolean updateAccount(Account account) throws SQLException, validatorExceptions {

		// Validate the account using AccountValidator
		if (AccountValidator.validate(account)) {

			// If validation passes, call the AccountDao to update the account
			return AccountDao.updateAccount(account);
		}

		// If validation fails, return false
		return false;
	}

	// Method to check if an account exists
	public static boolean exitsCheck(Account account) throws SQLException, validatorExceptions {

		// Validate the account using AccountValidator
		if (AccountValidator.validate(account)) {

			// If validation passes, call the AccountDao to check account existence
			return AccountDao.exitsCheck(account);
		}

		// If validation fails, return false
		return false;
	}

	// Method to check if there are inactive accounts and return true if found
	public static boolean getAllInactiveAccountNumber() throws SQLException {

		// Call AccountDao to retrieve a list of inactive account numbers
		ArrayList<String> list = AccountDao.getAllInactiveAccountNumber();

		// If the list is not empty, return true (inactive accounts exist)
		if (list.size() > 0) {
			return true;
		}

		// If the list is empty, return false (no inactive accounts)
		return false;
	}

	// Method to remove an account by account number
	public static boolean removeAccountByAccountNumber(String accountNumber) throws SQLException, validatorExceptions {

		// Validate the account number using AccountValidator
		if (AccountValidator.validateAccountNumber(accountNumber)) {

			// If validation passes, call the AccountDao to remove the account
			return AccountDao.removeAccountByAccountNumber(accountNumber);
		}

		// If validation fails, return false
		return false;
	}

	// Method to check if there are active accounts and return true if found
	public static boolean getAllActiveAccountNumber() throws SQLException {

		// Call AccountDao to retrieve a list of active account numbers
		ArrayList<String> list = AccountDao.getAllActiveAccountNumber();

		// If the list is not empty, return true (active accounts exist)
		if (list.size() > 0) {
			return true;
		}

		// If the list is empty, return false (no active accounts)
		return false;
	}
	
//	public static boolean removeAccountByAccountNumber(Account account) throws SQLException, validatorExceptions {
//
//		if (account == null) {
//
//			throw new IllegalArgumentException();
//		} else if (AccountValidator.validate(account)) {
//
//			return AccountDao.deleteAccount("0987654321123456");
//		}
//		return false;
//
//	}

//	public static void main(String[] args) throws SQLException, validatorExceptions {
//
//		Account ac = new Account("0987644221123456", "IDIB000K132", "9361320411", 1200.00, "SAVINGSACCOUNT");
//		addAccountToDataBase(ac);
//
//	}

}

