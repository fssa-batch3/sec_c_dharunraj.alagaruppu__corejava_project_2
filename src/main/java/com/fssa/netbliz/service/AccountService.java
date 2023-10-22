package com.fssa.netbliz.service;

import java.util.ArrayList;
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
	 * Validates the provided account details and retrieves bank details.
	 *
	 * @param account The Account object containing account details.
	 * @return True if the bank details were fetched successfully, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */

	public boolean getBankDetails(Account account) throws ServiceException {

		Account acc = new Account();

		try {
			if (AccountValidator.validateAccountNumber(account.getAccountNumber())
					&& AccountValidator.validateIfsc(account.getIfsc())
					&& AccountValidator.validatePhoneNumber(account.getPhoneNumber())) {

				acc = AccountDAO.getBankDetailsByAccountNumber(account);
				return addAccount(acc);
			} else {

				throw new ServiceException("Your bank details can't fetch the data from your respective bank");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {

			e.getMessage();
		}
		throw new ServiceException("Invalid account number or IFSC code");
	}

	/**
	 * Adds an account to the system if it is valid and not already available.
	 *
	 * @param account The Account object to add.
	 * @return True if the account was added successfully, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean addAccount(Account account) throws ServiceException {

		try {
			if (AccountValidator.validate(account) && !isAvailableAccount(account.getAccountNumber())) {
				return AccountDAO.addAccount(account);
			} else if (AccountValidator.validate(account) && isAvailableAccount(account.getAccountNumber())
					&& !isActiveAccount(account.getAccountNumber())) {

				return AccountDAO.existsCheck(account);
			} else {
				throw new ServiceException("Your account can't added !! Retry...!");
			}
		} catch (ValidatorException | ServiceException | DAOException e) {

			e.getMessage();
		}
		throw new ServiceException("Account details mismatch");

	}

	/**
	 * Removes an account by its account number if it is valid, available, and
	 * active.
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
			} else {
				throw new ServiceException("Your account can't deteted");
			}
		} catch (ValidatorException | ServiceException | DAOException e) {
			e.getMessage();
		}
		throw new ServiceException("Account details can't deleted");
	}

	/**
	 * Retrieves a list of accounts by phone number if the phone number is valid.
	 *
	 * @param phone The phone number to search for.
	 * @return A list of Account objects matching the phone number, or null if none
	 *         are found.
	 * @throws ServiceException If there is a service-level error.
	 */
	public List<Account> getAccountByPhoneNumber(long phone) throws ServiceException {
		List<Account> accountDetails = new ArrayList<>();

		try {
			if (AccountValidator.validatePhoneNumber(phone)) {

				accountDetails = AccountDAO.getAccountByPhoneNumber(phone);
			}
		} catch (ValidatorException | DAOException e) {
			e.getMessage();
		}

		return accountDetails;
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
			} else {
				throw new ServiceException("Your account is not available");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {
			e.getMessage();
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
			} else {
				throw new ServiceException("Your account is not active now please reactivate");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {
			e.getMessage();
		}
		return false;
	}

	/**
	 * Retrieves an account by its account number if it is valid, active, and
	 * available.
	 *
	 * @param accountNumber The account number to retrieve.
	 * @return The Account object representing the account details, or null if not
	 *         found.
	 * @throws ServiceException If there is a service-level error.
	 */
	public Account getAccountByNumber(String accountNumber) throws ServiceException {

		Account details = new Account();

		try {
			if (AccountValidator.validateAccountNumber(accountNumber) && isActiveAccount(accountNumber)
					&& isAvailableAccount(accountNumber)) {
				details = AccountDAO.getAccountByNumber(accountNumber);

			}
		} catch (ValidatorException | ServiceException | DAOException e) {
			e.getMessage();
		}

		return details;
	}
}
