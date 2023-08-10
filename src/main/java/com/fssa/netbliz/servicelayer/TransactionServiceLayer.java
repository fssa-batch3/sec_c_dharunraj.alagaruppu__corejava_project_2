package com.fssa.netbliz.servicelayer;

import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.dao.Logger;
import com.fssa.netbliz.dao.TransactionDao;
import com.fssa.netbliz.exception.AccountValidatorExceptions;
import com.fssa.netbliz.exception.DaoException;
import com.fssa.netbliz.exception.TransactionDaoException;
import com.fssa.netbliz.exception.TransactionValidatorExceptions;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.TransactionValidator;

public class TransactionServiceLayer {

	public static boolean moneyTransaction(Transaction trans)
			throws TransactionValidatorExceptions, AccountValidatorExceptions, TransactionDaoException, DaoException {

		if (TransactionValidator.validate(trans)) {

			return TransactionDao.updateHolderAccount(trans);
		}
		return false;
	}

	public static List<Object> listOfTransaction(String accNo)
			throws AccountValidatorExceptions, TransactionDaoException, DaoException {

		if (AccountValidator.validateAccountNumber(accNo)) {

			List<Object> list = new ArrayList<>();

			return TransactionDao.listTransaction(accNo);
		}
		return null;

	}

	public static boolean printTransactions(String accNo)
			throws TransactionDaoException, DaoException, AccountValidatorExceptions {

		if (AccountValidator.validateAccountNumber(accNo)) {

			return TransactionDao.printTransactions(accNo);
		}
		return false;
	}

	public static boolean isActiveAccount(String accNo) throws TransactionDaoException, DaoException {

		if (TransactionDao.isActiveAccount(accNo)) {

			Logger.info(accNo + " is currect active");
			return true;
		}
		return false;
	}

}
