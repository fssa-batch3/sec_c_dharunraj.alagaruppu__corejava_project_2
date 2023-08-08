package com.fssa.netbliz.validator;

import com.fssa.error.AccountValidatorErrors;
import com.fssa.error.TransactionValidatorErrors;
import com.fssa.netbliz.exception.AccountValidatorExceptions;
import com.fssa.netbliz.exception.TransactionValidatorExceptions;
import com.fssa.netbliz.model.Transaction;

public class TransactionValidator {

	public static final int REMARK_LENGTH = 30;

	public static final int MINIMUM_TRANSFER_AMOUNT = 0;

	public static boolean validate(Transaction trans)
			throws TransactionValidatorExceptions, AccountValidatorExceptions {

		if (trans == null) { 

			throw new TransactionValidatorExceptions(AccountValidatorErrors.INVALID_OBJECT_NULL);
		}
		AccountValidator.validateAccountNumber(trans.getAccountHolderAccNo());
		AccountValidator.validateAccountNumber(trans.getRemittanceAccNo());
		AccountValidator.validateIfsc(trans.getReceiverIfscCode());
		validateAmount(trans.getTransfer_amount());
		validateRemark(trans.getRemark());
		return true;
	}

	public static boolean validateAmount(double amount) throws TransactionValidatorExceptions {

		if (amount > MINIMUM_TRANSFER_AMOUNT) {

			return true;
		}
		throw new TransactionValidatorExceptions(TransactionValidatorErrors.INVALID_AMOUNT);

	}

	public static boolean validateRemark(String remark) throws TransactionValidatorExceptions {

		if (remark.length() < REMARK_LENGTH) {

			return true;
		}

		throw new TransactionValidatorExceptions(TransactionValidatorErrors.INVALID_REMARK);
	}
	
}
