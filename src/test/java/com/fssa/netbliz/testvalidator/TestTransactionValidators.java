package com.fssa.netbliz.testvalidator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.error.TransactionValidatorErrors;
import com.fssa.netbliz.exception.AccountValidatorExceptions;
import com.fssa.netbliz.exception.TransactionValidatorExceptions;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.validator.TransactionValidator;

class TestTransactionValidators {

	Transaction trans = new Transaction();

	@Test
	void testValidateAmount() throws TransactionValidatorExceptions {
		trans.setTransferAmount(10.0);

		// Validate a valid transfer amount and assert true
		Assertions.assertTrue(TransactionValidator.validateAmount(trans.getTransferAmount()));
	}

	@Test
	void testInValidAmount() throws TransactionValidatorExceptions {
		try {
			// Validate an invalid transfer amount and assert the correct exception message
			TransactionValidator.validateAmount(0);
		} catch (Exception e) {
			Assertions.assertEquals(TransactionValidatorErrors.INVALID_AMOUNT, e.getMessage());
		}
	}

	@Test
	void testValidateRemark() throws TransactionValidatorExceptions {
		trans.setRemark("bill payment");

		// Validate a valid remark and assert true
		Assertions.assertTrue(TransactionValidator.validateRemark(trans.getRemark()));
	}

	@Test
	void testInValidRemark() throws TransactionValidatorExceptions {
		try {
			// Validate an invalid remark and assert the correct exception message
			TransactionValidator.validateRemark(
					"if your paying an electricity bills write remark electric bill. The same will reflect in your passbook when you update it.");
		} catch (Exception e) {
			Assertions.assertEquals(TransactionValidatorErrors.INVALID_REMARK, e.getMessage());
		}
	}

	@Test
	void testNullValidate() throws TransactionValidatorExceptions {
		try {
			// Validate null Transaction and assert the correct exception message
			TransactionValidator.validate(null);
		} catch (Exception e) {
			Assertions.assertEquals(TransactionValidatorErrors.INVALID_OBJECT_NULL, e.getMessage());
		}
	}

	@Test
	void testValidate() throws TransactionValidatorExceptions, AccountValidatorExceptions {
		Transaction trans = new Transaction("1234567890123456", "0987654321123456", "IDIB000K132", 10, "bill pay");

		// Validate a valid Transaction and assert true
		Assertions.assertTrue(TransactionValidator.validate(trans));
	}
}
