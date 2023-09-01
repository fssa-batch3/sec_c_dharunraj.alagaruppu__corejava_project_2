package com.fssa.netbliz.service;

import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.dao.TransactionDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.TransactionValidator;

public class TransactionService {

	public static boolean moneyTransaction(Transaction trans) throws ValidatorException, DAOException {
		if (TransactionValidator.validate(trans)) {
			return TransactionDAO.updateHolderAccount(trans);
		}
		return false;
	}

	public static List<Transaction> listOfTransaction(String accNo) throws ValidatorException, DAOException {
		List<Object> list = new ArrayList<>();
		if (AccountValidator.validateAccountNumber(accNo)) {
			return TransactionDAO.listTransaction(accNo);  
		}
		return null;
	}

	public static boolean printTransactions(String accNo) throws ValidatorException, DAOException {
		if (AccountValidator.validateAccountNumber(accNo)) {
			return TransactionDAO.printTransactions(accNo);
		}
		return false;
	}

}
