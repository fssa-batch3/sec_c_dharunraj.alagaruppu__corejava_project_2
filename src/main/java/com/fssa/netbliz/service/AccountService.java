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

	/**
	 * Adds an account to the system.
	 *
	 * @param account The account to add.
	 * @return True if the account was added successfully, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean addAccount(Account account) throws ServiceException {
		try {
			if (AccountValidator.validate(account)) {
				return AccountDAO.addAccount(account);
			}
		} catch (ValidatorException e) {
			throw new ServiceException("Validation error: " + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException("DAO error: " + e.getMessage());
		}
		return false;
	}

	/**
	 * Removes an account by its account number.
	 *
	 * @param accountNumber The account number to remove.
	 * @return True if the account was removed successfully, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean removeAccountByAccountNumber(String accountNumber) throws ServiceException {
		try {
			if (AccountValidator.validateAccountNumber(accountNumber) && isAvailableAccount(accountNumber)
					&& isActiveAccount(accountNumber)) {
				return AccountDAO.removeAccountByAccountNumber(accountNumber);
			}
		} catch (ValidatorException e) {
			throw new ServiceException("Validation error: " + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException("DAO error: " + e.getMessage());
		}
		return false;
	}

	/**
	 * Retrieves a list of accounts by phone number.
	 *
	 * @param phone The phone number to search for.
	 * @return A list of accounts matching the phone number, or null if none are
	 *         found.
	 * @throws ServiceException If there is a service-level error.
	 */
	public List<Account> getAccountByPhoneNumber(long phone) throws ServiceException {
		try {
			if (AccountValidator.validatePhoneNumber(phone)) {
				return AccountDAO.getAccountByPhoneNumber(phone);
			}
		} catch (ValidatorException e) {
			throw new ServiceException("Validation error: " + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException("DAO error: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Checks if an account with the given account number is available.
	 *
	 * @param accNo The account number to check.
	 * @return True if the account is available, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean isAvailableAccount(String accNo) throws ServiceException {
		try {
			if (AccountValidator.validateAccountNumber(accNo)) {
				return AccountDAO.isAvailableAccount(accNo);
			}
		} catch (ValidatorException e) {
			throw new ServiceException("Validation error: " + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException("DAO error: " + e.getMessage());
		}
		return false;
	}

	/**
	 * Checks if an account with the given account number is active.
	 *
	 * @param accNo The account number to check.
	 * @return True if the account is active, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean isActiveAccount(String accNo) throws ServiceException {
	    try {
	        if (AccountValidator.validateAccountNumber(accNo)) {
	            return AccountDAO.isActiveAccount(accNo);
	        }
	    } catch (ValidatorException e) {
	        throw new ServiceException("Validation error: " + e.getMessage());
	    } catch (DAOException e) {
	        throw new ServiceException("DAO error: " + e.getMessage());
	    }
	    return false;
	}

	/**
	 * Retrieves a list of accounts by account number, if the account is active and
	 * available.
	 *
	 * @param accountNumber The account number to search for.
	 * @return A list of accounts matching the account number, or null if none are
	 *         found.
	 * @throws ServiceException If there is a service-level error.
	 */
	public List<Account> getAccountByNumber(String accountNumber) throws ServiceException {
		try {
			if (AccountValidator.validateAccountNumber(accountNumber) && isActiveAccount(accountNumber)
					&& isAvailableAccount(accountNumber)) {
				return AccountDAO.getAccountByNumber(accountNumber);
			}
		} catch (ValidatorException e) {
			throw new ServiceException("Validation error: " + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException("DAO error: " + e.getMessage());
		}
		return null;
	}

}
