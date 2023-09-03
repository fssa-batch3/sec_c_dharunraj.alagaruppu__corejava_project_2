package com.fssa.netbliz.service;

import java.util.List;

import com.fssa.netbliz.dao.AccountDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.exception.ValidatorException;
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
	public AccountService() {
//		private constructor
	}

	public boolean addAccount(Account account) throws ServiceException {

		// Validate the account using AccountValidator
		try {

			if (AccountValidator.validate(account)) {

				// If validation passes, call the AccountDao to check account existence
				return AccountDAO.addAccount(account);
			}
		} catch (ValidatorException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}
		// If validation fails, return false
		return false;
	}

	// Method to remove an account by account number
	public boolean removeAccountByAccountNumber(String accountNumber) throws ServiceException {

		// Validate the account number using AccountValidator
		try {
			if (AccountValidator.validateAccountNumber(accountNumber) && isAvailableAccount(accountNumber)
					&& isActiveAccount(accountNumber)) {

				// If validation passes, call the AccountDao to remove the account
				return AccountDAO.removeAccountByAccountNumber(accountNumber);
			}
		} catch (ValidatorException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}

		// If validation fails, return false
		return false;
	}

	public List<Account> getAccountByPhoneNumber(long phone) throws ServiceException {

		try {

			if (AccountValidator.validatePhoneNumber(phone)) {

				return AccountDAO.getAccountByPhoneNumber(phone);

			}
		} catch (ValidatorException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}
		return null;
	}

	public boolean isAvailableAccount(String accNo) throws ServiceException {

		try {
			if (AccountValidator.validateAccountNumber(accNo)) {

				return AccountDAO.isAvailableAccount(accNo);
			}
		} catch (ValidatorException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	// Checks if an account is active
	public boolean isActiveAccount(String accNo) throws ServiceException {
		
			try {
				if (AccountValidator.validateAccountNumber(accNo)) {
					return AccountDAO.isActiveAccount(accNo);
				} 
			} catch (ValidatorException e) {
				e.getMessage();
				throw new ServiceException(e.getMessage());
			} catch (DAOException e) {
				e.getMessage();
				throw new ServiceException(e.getMessage());
			}
		return false;
	}

	public List<Account> getAccountByNumber(String accountNumber) throws ServiceException {

		try {

			if (AccountValidator.validateAccountNumber(accountNumber) && isActiveAccount(accountNumber)
					&& isAvailableAccount(accountNumber)) {

				return AccountDAO.getAccountByNumber(accountNumber);

			}
		} catch (ValidatorException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}
		return null;

	}
}
