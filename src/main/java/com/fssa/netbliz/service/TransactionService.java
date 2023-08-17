package com.fssa.netbliz.service;

import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.dao.Logger;
import com.fssa.netbliz.dao.TransactionDao;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.TransactionDaoException;
import com.fssa.netbliz.exception.TransactionValidatorException;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.TransactionValidator;

public class TransactionService {

	// Performs money transaction between accounts
	public static boolean moneyTransaction(Transaction trans)
			throws TransactionValidatorException, AccountValidatorException, TransactionDaoException {
		if (TransactionValidator.validate(trans)) {
			return TransactionDao.updateHolderAccount(trans);
		}
		return false;
	}

	// A list of transactions for a given account number
	public static List<Object> listOfTransaction(String accNo)
			throws AccountValidatorException, TransactionDaoException {
		List<Object> list = new ArrayList<>();
		if (AccountValidator.validateAccountNumber(accNo)) {
			return TransactionDao.listTransaction(accNo);
		}
		return list;
	}

	// Prints transactions for a given account number
	public static boolean printTransactions(String accNo) throws TransactionDaoException, AccountValidatorException {
		if (AccountValidator.validateAccountNumber(accNo)) {
			return TransactionDao.printTransactions(accNo);
		}
		return false;
	}

	// Checks if an account is active
	public static boolean isActiveAccount(String accNo) throws TransactionDaoException {
		if (TransactionDao.isActiveAccount(accNo)) {
			Logger.info(accNo + " is currently active");
			return true; 
		}
		return false;
	}
}
