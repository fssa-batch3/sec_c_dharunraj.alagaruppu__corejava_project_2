package com.fssa.netbliz.service;

import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.dao.TransactionDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.validator.CustomerValidator;
import com.fssa.netbliz.validator.TransactionValidator;

public class TransactionService {

	public boolean moneyTransaction(Transaction trans) throws ServiceException {
		try {
			if (TransactionValidator.validate(trans)) {
				return TransactionDAO.updateHolderAccount(trans);
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

	public List<Transaction> listOfTransaction(int id) throws ServiceException {
		List<Transaction> list = new ArrayList<>();

		try {
			if (CustomerValidator.validateCustomerId(id)) {
				return TransactionDAO.listTransaction(id);
			}
		} catch (ValidatorException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	public boolean printTransactions(int id) throws ServiceException {
		try {
			if (CustomerValidator.validateCustomerId(id)) {
				return TransactionDAO.printTransactions(id);
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

}
