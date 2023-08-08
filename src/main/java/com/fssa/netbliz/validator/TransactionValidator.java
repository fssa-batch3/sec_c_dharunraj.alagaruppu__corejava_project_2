package com.fssa.netbliz.validator;

import com.fssa.error.AccountValidatorErrors;
import com.fssa.error.TransactionValidatorErrors;
import com.fssa.netbliz.exception.AccountValidatorExceptions;
import com.fssa.netbliz.exception.TransactionValidatorExceptions;
import com.fssa.netbliz.model.Transaction;

public class TransactionValidator {

	public static final int MINIMUM_TRNASFER_AMOUNT = 1;

	public static final int MAX_LENGTH_REMARK = 30; 

	public static boolean validate(Transaction trans)
			throws TransactionValidatorExceptions, AccountValidatorExceptions {

		if (trans == null) {

			throw new TransactionValidatorExceptions(TransactionValidatorErrors.INVALID_OBJECT_NULL);
		}
		AccountValidator.validateAccountNumber(trans.getAccountHolderAccNo());
		AccountValidator.validateAccountNumber(trans.getRemittanceAccNo());
		AccountValidator.validateIfsc(trans.getReceiverIfscCode());
		validateAmount(trans.getTransfer_amount());
		validateRemark(trans.getRemark());
		return true;
	}

	public static boolean validateAmount(double amount) throws TransactionValidatorExceptions {

		if (amount >= MINIMUM_TRNASFER_AMOUNT) {

			return true;
		}
		throw new TransactionValidatorExceptions(TransactionValidatorErrors.INVALID_AMOUNT);

	}

	public static boolean validateRemark(String remark) throws TransactionValidatorExceptions {

		if (remark.length() < MAX_LENGTH_REMARK) {

			return true;
		}

		throw new TransactionValidatorExceptions(TransactionValidatorErrors.INVALID_REMARK);
	}
}
