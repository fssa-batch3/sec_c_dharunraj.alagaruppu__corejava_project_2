package com.fssa.netbliz.service;

import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.dao.TransactionDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.CustomerValidator;
import com.fssa.netbliz.validator.TransactionValidator;

public class TransactionService {

	/**
	 * Perform a money transaction and update the holder's account if the provided
	 * transaction data is valid.
	 *
	 * @param trans The Transaction object representing the transaction to perform.
	 * @return True if the transaction is successful, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean moneyTransaction(Transaction trans) throws ServiceException {

		try {
			if (TransactionValidator.validate(trans)) {
				return TransactionDAO.updateHolderAccount(trans);
			} else {
				throw new ServiceException("Your transaction is failed");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {
			e.getMessage();
		}
		throw new ServiceException("Transaction failed");

	}

	/**
	 * Get a list of transactions for a given customer ID.
	 *
	 * @param id The customer ID to retrieve transactions for.
	 * @return A list of Transaction objects representing the customer's
	 *         transactions, or an empty list if none are found.
	 * @throws ServiceException If there is a service-level error.
	 */
	public List<Transaction> listOfTransaction(int id) throws ServiceException {
		List<Transaction> list = new ArrayList<>();

		try {
			if (CustomerValidator.validateCustomerId(id)) {
				return TransactionDAO.listTransaction(id);
			}
		} catch (ValidatorException | DAOException e) {
			e.getMessage();
		}

		return list;
	}

	/**
	 * Print the transactions for a given customer ID.
	 *
	 * @param id The customer ID to print transactions for.
	 * @return True if printing is successful, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean printTransactions(int id) throws ServiceException {

		try {
			if (CustomerValidator.validateCustomerId(id)) {
				return TransactionDAO.printTransactions(id);
			} else {
				throw new ServiceException("Your transaction can't listed");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {
			e.getMessage();
		}
		return false;

	}

	/**
	 * Check if the minimum balance is maintained for a given account and transfer
	 * amount.
	 *
	 * @param accNo         The account number to check.
	 * @param transferMoney The amount of money to transfer.
	 * @return True if the minimum balance is maintained, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */

	public boolean checkMinimumBalance(String accNo, double transferMoney) throws ServiceException {

		try {
			if (AccountValidator.validateAccountNumber(accNo) && TransactionValidator.validateAmount(transferMoney)) {
				return TransactionDAO.checkMinimumBalance(accNo, transferMoney);
			} else {
				throw new ServiceException("Your minimum balance is not maintained");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {
			e.getMessage();
		}
		return false;

	}

	/**
	 * Check conditions for the account holder before performing a transaction.
	 *
	 * @param trans The Transaction object representing the transaction details.
	 * @return True if the account holder conditions are met, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */

	public boolean holderConditions(Transaction trans) throws ServiceException {

		try {
			if (TransactionValidator.validate(trans)) {
				return TransactionDAO.accountHolderCheck(trans);
			} else {
				throw new ServiceException("sender details fail");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {
			e.getMessage();
		}
		return false;

	}

	/**
	 * Check conditions for the remittance account before performing a transaction.
	 *
	 * @param trans The Transaction object representing the transaction details.
	 * @return True if the remittance account conditions are met, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */

	public boolean remittanceConditions(Transaction trans) throws ServiceException {

		try {
			if (TransactionValidator.validate(trans)) {
				return TransactionDAO.remittanceAccountCheck(trans);
			} else {
				throw new ServiceException("receiver details fail");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {
			e.getMessage();
		}
		return false;

	}

}
