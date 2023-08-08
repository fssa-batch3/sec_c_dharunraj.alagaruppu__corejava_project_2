package com.fssa.netbliz.serviceLayer;

import java.sql.SQLException;

import com.fssa.netbliz.dao.TransactionDao;
import com.fssa.netbliz.exception.AccountValidatorExceptions;
import com.fssa.netbliz.exception.TransactionDaoException;
import com.fssa.netbliz.exception.TransactionValidatorExceptions;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.TransactionValidator;

public class TransactionServiceLayer {

	public static boolean moneyTransaction(Transaction trans)
			throws TransactionValidatorExceptions, AccountValidatorExceptions, TransactionDaoException{
		
		
		if(TransactionValidator.validate(trans)) {
			
			return TransactionDao.updateHolderAccount(trans);
		}
		return false;
	}
}
