package com.fssa.netbliz.service;

import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.dao.Logger;
import com.fssa.netbliz.dao.TransactionDAO;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.TransactionDAOException;
import com.fssa.netbliz.exception.TransactionValidatorException;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.TransactionValidator;

public class TransactionService {

	
	public static boolean moneyTransaction(Transaction trans)
			throws TransactionValidatorException, AccountValidatorException, TransactionDAOException {
		if (TransactionValidator.validate(trans)) {
			return TransactionDAO.updateHolderAccount(trans);
		}
		return false;
	}

	public static List<Object> listOfTransaction(String accNo)
			throws AccountValidatorException, TransactionDAOException {
		List<Object> list = new ArrayList<>();
		if (AccountValidator.validateAccountNumber(accNo)) {
			return TransactionDAO.listTransaction(accNo);
		}
		return list;
	} 

	// Prints transactions for a given account number
	public static boolean printTransactions(String accNo) throws TransactionDAOException, AccountValidatorException {
		if (AccountValidator.validateAccountNumber(accNo)) {
			return TransactionDAO.printTransactions(accNo);
		}
		return false;
	}


}
