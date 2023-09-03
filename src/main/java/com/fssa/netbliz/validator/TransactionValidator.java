package com.fssa.netbliz.validator;

import com.fssa.netbliz.error.TransactionValidatorError;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Transaction;

public class TransactionValidator {

	private TransactionValidator() {
		// Private constructor to prevent instantiation
	}

	public static final int MINIMUM_TRANSFER_AMOUNT = 1;
	public static final int MAX_LENGTH_REMARK = 50;

	/**
	 * Validates a Transaction object by checking its components for correctness.
	 * This method ensures that the provided Transaction object is not null,
	 * validates the account numbers, IFSC code, transfer amount, and remark
	 * associated with the transaction.
	 *
	 * @param trans The Transaction object to be validated.
	 * @return True if the Transaction passes all validation checks, otherwise
	 *         false.
	 * @throws TransactionValidatorException If there are issues with the validation
	 *                                       process.
	 * @throws AccountValidatorException     If there are issues with the Account
	 *                                       validation process.
	 */

	public static boolean validate(Transaction trans) throws ValidatorException { 
		if (trans == null) {
			throw new ValidatorException(TransactionValidatorError.INVALID_OBJECT_NULL);
		}

		AccountValidator.validateAccountNumber(trans.getAccountHolderAccNo());
		AccountValidator.validateAccountNumber(trans.getRemittanceAccNo());
		AccountValidator.validateIfsc(trans.getReceiverIfscCode());
		validateAmount(trans.getTransferAmount());
		validateRemark(trans.getRemark());
		return true;
	}

	/**
	 * Validates the amount of a transaction to ensure it meets the minimum transfer
	 * amount criteria.
	 *
	 * @param amount The amount to be validated.
	 * @return True if the amount is valid and meets the criteria, otherwise false.
	 * @throws TransactionValidatorException If the amount is invalid.
	 */

	public static boolean validateAmount(double amount) throws ValidatorException {
		if (amount >= MINIMUM_TRANSFER_AMOUNT) {
			return true;
		}
		throw new ValidatorException(TransactionValidatorError.INVALID_AMOUNT);
	}

	/**
	 * Validates the remark associated with a transaction to ensure its length is
	 * within the allowable limit.
	 *
	 * @param remark The remark to be validated.
	 * @return True if the remark is valid and within the allowable length,
	 *         otherwise false.
	 * @throws TransactionValidatorException If the remark is invalid.
	 */

	public static boolean validateRemark(String remark) throws ValidatorException {
		if (remark.length() <= MAX_LENGTH_REMARK) {
			return true;
		}
		throw new ValidatorException(TransactionValidatorError.INVALID_REMARK);
	}
}
