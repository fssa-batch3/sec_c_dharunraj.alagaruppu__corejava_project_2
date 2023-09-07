package com.fssa.netbliz.service;

import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.constants.NetblizConstants;
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
	 * Perform a money transaction and update the holder's account.
	 *
	 * @param trans The transaction to perform.
	 * @return True if the transaction is successful, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean moneyTransaction(Transaction trans) throws ServiceException {
		try {
			if (TransactionValidator.validate(trans)) {
				return TransactionDAO.updateHolderAccount(trans);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
		return false;
	}

	/**
	 * Get a list of transactions for a given customer ID.
	 *
	 * @param id The customer ID to retrieve transactions for.
	 * @return A list of transactions for the customer, or an empty list if none are
	 *         found.
	 * @throws ServiceException If there is a service-level error.
	 */
	public List<Transaction> listOfTransaction(int id) throws ServiceException {
		List<Transaction> list = new ArrayList<>();

		try {
			if (CustomerValidator.validateCustomerId(id)) {
				return TransactionDAO.listTransaction(id);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
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
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
		return false;
	}

	public boolean checkMinimumBalance(String accNo, double transferMoney) throws ServiceException {

		try {
			if (AccountValidator.validateAccountNumber(accNo) && TransactionValidator.validateAmount(transferMoney)) { 
				return TransactionDAO.checkMinimumBalance(accNo, transferMoney);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
		return false;
	}

}
