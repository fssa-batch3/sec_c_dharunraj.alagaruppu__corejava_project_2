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
	void testvalidateAmount() throws TransactionValidatorExceptions {

		trans.setTransferAmount(10.0);

		Assertions.assertTrue(TransactionValidator.validateAmount(trans.getTransferAmount()));

	}

	@Test
	void testInValidAmount() throws TransactionValidatorExceptions {

		try {

			TransactionValidator.validateAmount(0);
		} catch (Exception e) {

			Assertions.assertEquals(TransactionValidatorErrors.INVALID_AMOUNT, e.getMessage());
		}
	}

	@Test
	void testremark() throws TransactionValidatorExceptions {

		trans.setRemark("bill payment");
		Assertions.assertTrue(TransactionValidator.validateRemark(trans.getRemark()));
	}

	@Test
	void testInValidRemark() throws TransactionValidatorExceptions {

		try {

			TransactionValidator.validateRemark(
					"if your paying an electricity bills write remark electric bill.the same will reflect in your passbook when you update it.");

		}

		catch (Exception e) {

			Assertions.assertEquals(TransactionValidatorErrors.INVALID_REMARK, e.getMessage());
		}
	}

	@Test
	void testNullValidate() throws TransactionValidatorExceptions {

		try {
			TransactionValidator.validate(null);
		}

		catch (Exception e) {

			Assertions.assertEquals(TransactionValidatorErrors.INVALID_OBJECT_NULL, e.getMessage());
		}
	}

	@Test
	void testValidate() throws TransactionValidatorExceptions, AccountValidatorExceptions {

		Transaction trans = new Transaction("1234567890123456", "0987654321123456", "IDIB000K132", 10, "bill pay");
		Assertions.assertTrue(TransactionValidator.validate(trans));
	}

	// assertThrows(IllegalArgumentException.class, () -> {
	// MathUtils.divide(10, 0);
	// });
}
