package com.fssa.netbliz.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.netbliz.error.TransactionValidatorError;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Transaction;

class TestTransactionValidator {

	Transaction trans = new Transaction();

	@Test
	void testValidateAmount() throws ValidatorException {
		trans.setTransferAmount(10.0);

		// Validate a valid transfer amount and assert true
		Assertions.assertTrue(TransactionValidator.validateAmount(trans.getTransferAmount()));
	}

	@Test
	void testInValidAmount() throws ValidatorException {
		try {
			// Validate an invalid transfer amount and assert the correct exception message
			TransactionValidator.validateAmount(0);
		} catch (Exception e) {
			Assertions.assertEquals(TransactionValidatorError.INVALID_AMOUNT, e.getMessage());
		}
	}

	@Test
	void testValidateRemark() throws ValidatorException {
		trans.setRemark("bill payment");

		// Validate a valid remark and assert true
		Assertions.assertTrue(TransactionValidator.validateRemark(trans.getRemark()));
	}

	@Test
	void testInValidRemark() throws ValidatorException {
		try {
			// Validate an invalid remark and assert the correct exception message
			TransactionValidator.validateRemark(
					"if your paying an electricity bills write remark electric bill. The same will reflect in your passbook when you update it.");
		} catch (Exception e) {
			Assertions.assertEquals(TransactionValidatorError.INVALID_REMARK, e.getMessage());
		}
	}

	@Test
	void testNullValidate() throws ValidatorException {
		try {
			// Validate null Transaction and assert the correct exception message
			TransactionValidator.validate(null);
		} catch (Exception e) {
			Assertions.assertEquals(TransactionValidatorError.INVALID_OBJECT_NULL, e.getMessage());
		}
	}

	@Test
	void testValidate() throws ValidatorException {
		Transaction trans = new Transaction("1234567890123456", "0987654321123456", "IDIB000K132", 10, "bill pay");

		// Validate a valid Transaction and assert true
		Assertions.assertTrue(TransactionValidator.validate(trans));
	}
}
