package com.fssa.netbliz.testValidator;

import com.fssa.errors.TransactionValidatorErrors;
import com.fssa.netbliz.exception.TransactionDaoException;
import com.fssa.netbliz.exception.TransactionValidatorExceptions;

public class TestTransactionValidators {

	public static final int REMARK_LENGTH = 30;

	public static boolean remarkValidator(String remark) throws TransactionValidatorExceptions {

		if (remark.length() < REMARK_LENGTH) {

			throw new TransactionValidatorExceptions(TransactionValidatorErrors.REMARK_LENGTH_OVERLOAD);
		}
		return true;
	} 
}
