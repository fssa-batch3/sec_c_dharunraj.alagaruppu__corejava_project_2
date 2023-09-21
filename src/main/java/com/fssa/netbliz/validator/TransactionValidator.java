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
	 *
	 * @param trans The Transaction object to be validated.
	 * @return True if the Transaction passes all validation checks, otherwise
	 *         false.
	 * @throws ValidatorException If there are issues with the validation process.
	 */
	public static boolean validate(Transaction trans) throws ValidatorException {
		if (trans == null) {
			System.out.println("trans null");
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
	 * @param amount The amount to be validated.
	 * @return True if the amount is valid and meets the criteria, otherwise false.
	 * @throws ValidatorException If the amount is invalid.
	 */
	public static boolean validateAmount(double amount) throws ValidatorException {
		if (amount >= MINIMUM_TRANSFER_AMOUNT) {
			return true;
		}
		throw new ValidatorException(TransactionValidatorError.INVALID_AMOUNT);
	}

	/**
	 *
	 * @param remark The remark to be validated.
	 * @return True if the remark is valid and within the allowable length,
	 *         otherwise false.
	 * @throws ValidatorException If the remark is invalid.
	 */
	public static boolean validateRemark(String remark) throws ValidatorException {
		if (remark.length() <= MAX_LENGTH_REMARK) {
			return true;
		}
		throw new ValidatorException(TransactionValidatorError.INVALID_REMARK);
	}
}
