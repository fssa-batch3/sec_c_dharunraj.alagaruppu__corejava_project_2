package com.fssa.netbliz.service;

import java.util.List;

import com.fssa.netbliz.dao.AccountDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.util.Logger;
import com.fssa.netbliz.validator.AccountValidator;

/**
 * @author DharunrajAlagaruppu
 * 
 *         This AccountServiceLayer is a class it's used to validate via
 *         validator method. It's working as a service layer of the account
 *         class.
 */

public class AccountService {
	public AccountService() {
//		private constructor
	}

	public static final int ZERO = 0;
	
	public static List<Account> getAccount(String accountNumber) throws ServiceException {

		try { 

			if (AccountValidator.validateAccountNumber(accountNumber)) {

				try {
					return AccountDAO.getAccountByNumber(accountNumber);
				} catch (DAOException e) {

					throw new ServiceException(e.getMessage()); 
				}
			}
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		} 
		return null; 

	}

	// Method to check if an account exists
	public static boolean exitsCheck(Account account) throws ServiceException {

		// Validate the account using AccountValidator
		try {
			if (AccountValidator.validate(account)) {

				// If validation passes, call the AccountDao to check account existence
				return AccountDAO.existsCheck(account);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		} 

		// If validation fails, return false
		return false;
	}

	// Method to check if there are inactive accounts and return true if found
	public static boolean getAllInactiveAccountNumber() throws ServiceException {

		// Call AccountDao to retrieve a list of inactive account numbers
		List<String> list;
		try {
			list = AccountDAO.getAllInactiveAccountNumber();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		// If the list is not empty, return true (inactive accounts exist)

		return (list.size() > ZERO);

	}

	// Method to remove an account by account number
	public static boolean removeAccountByAccountNumber(String accountNumber) throws ServiceException { 

		// Validate the account number using AccountValidator
		try {
			if (AccountValidator.validateAccountNumber(accountNumber)) {

				// If validation passes, call the AccountDao to remove the account
				return AccountDAO.removeAccountByAccountNumber(accountNumber);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		// If validation fails, return false
		return false;
	}

	// Checks if an account is active
	public static boolean isActiveAccount(String accNo) throws ServiceException {
		try {
			if (AccountDAO.isActiveAccount(accNo)) {
				Logger.info(accNo + " is currently active");
				return true;
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return false;
	}
}
