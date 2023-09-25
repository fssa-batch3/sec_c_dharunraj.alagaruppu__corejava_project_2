package com.fssa.netbliz.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.constants.NetblizConstants;
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

	public boolean getBankDetails(Account account) throws ServiceException {

		Account acc = new Account();
		try {
			if (AccountValidator.validateAccountNumber(account.getAccountNumber())
					&& AccountValidator.validateIfsc(account.getIfsc())
					&& AccountValidator.validatePhoneNumber(account.getPhoneNumber())) {

				acc = AccountDAO.getBankDetailsByAccountNumber(account);
				return addAccount(acc);
			}
			else {
				
				throw new ServiceException("Your bank details can't fetch the data from your respective bank");
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
		
	}

	/**
	 * Adds an account to the system.
	 *
	 * @param account The account to add.
	 * @return True if the account was added successfully, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 * @throws SQLException
	 */
	public boolean addAccount(Account account) throws ServiceException {
		try {
			if (AccountValidator.validate(account) && !isAvailableAccount(account.getAccountNumber())) {
				return AccountDAO.addAccount(account);
			} else if (AccountValidator.validate(account) && isAvailableAccount(account.getAccountNumber())
					&& !isActiveAccount(account.getAccountNumber())) {

				return AccountDAO.existsCheck(account);
			}
			else {
				throw new ServiceException("Your account can't added !! Retry...!");
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
		
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
			else {
				throw new ServiceException("Your account can't deteted");
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
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
		List<Account> accountDetails = new ArrayList<>();
		try {
			if (AccountValidator.validatePhoneNumber(phone)) {

				accountDetails = AccountDAO.getAccountByPhoneNumber(phone);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
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
			}
			else {
				throw new ServiceException("Your account is not available");
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
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
			else {
				throw new ServiceException("Your account is not active now please reactivate");
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
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
	public Account getAccountByNumber(String accountNumber) throws ServiceException {

		Account details = new Account(); 
		try {
			if (AccountValidator.validateAccountNumber(accountNumber) && isActiveAccount(accountNumber)
					&& isAvailableAccount(accountNumber)) {
				details = AccountDAO.getAccountByNumber(accountNumber);

			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
		return details;
	}

	public static void main(String[] args) throws ServiceException, SQLException {

		Account account = new Account("1234567890123455", "IDIB000K132", 9361320511l);

		AccountService as = new AccountService();
		System.out.println(as.getBankDetails(account));
	}

}
