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

	public static boolean addAccount(Account account) throws ServiceException {

		// Validate the account using AccountValidator
		try {
			if (AccountValidator.validate(account) && !isAvailableAccount(account.getAccountNumber())) {

				// If validation passes, call the AccountDao to check account existence
				return AccountDAO.addAccount(account);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		// If validation fails, return false
		return false;
	}

	public static boolean isAvailableAccount(String accNo) throws ServiceException {

		try {
			if (AccountValidator.validateAccountNumber(accNo)) {

				return AccountDAO.isAvailableAccount(accNo);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	// Method to check if an account exists
	public static boolean exitsCheck(Account account) throws ServiceException {

		// Validate the account using AccountValidator
		try {
			if (AccountValidator.validate(account) && !isActiveAccount(account.getAccountNumber())
					&& isAvailableAccount(account.getAccountNumber())) { 

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

	// Checks if an account is active
	public static boolean isActiveAccount(String accNo) throws ServiceException {
		try {
			if (AccountValidator.validateAccountNumber(accNo)) {
				return AccountDAO.isActiveAccount(accNo);
			}

		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	public static List<Account> getAccountByNumber(String accountNumber) throws ServiceException {

		try { 

			if (AccountValidator.validateAccountNumber(accountNumber) && isActiveAccount(accountNumber)
					&& isAvailableAccount(accountNumber)) { 

				return AccountDAO.getAccountByNumber(accountNumber);

			}
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {

			throw new ServiceException(e.getMessage());
		}
		return null;

	}

	// Method to remove an account by account number
	public static boolean removeAccountByAccountNumber(String accountNumber) throws ServiceException {

		// Validate the account number using AccountValidator
		try {
			if (AccountValidator.validateAccountNumber(accountNumber) && isAvailableAccount(accountNumber)
					&& isActiveAccount(accountNumber)) {

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
}
