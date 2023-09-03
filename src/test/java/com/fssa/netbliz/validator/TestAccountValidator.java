package com.fssa.netbliz.validator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.netbliz.enums.AccountEnum;
import com.fssa.netbliz.error.AccountValidatorError;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Account;

/**
 * A class containing test cases for the AccountValidator class.
 */

 class TestAccountValidator {

	Account account = new Account();

	@Test 

	/**
	 * Test case for validating a valid Account object.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */

	 void testValidObject() throws ValidatorException {
 
		Account account = new Account("1234567091123456", "IDIB000K132", 9361320511l, 1000.0, AccountEnum.SAVINGS);

		Assertions.assertTrue(AccountValidator.validate(account));
	}

	@Test

	/**
	 * Test case for validating an invalid (null) Account object.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */

	 void testInvalidObject() throws ValidatorException {

		try {

			AccountValidator.validate(null);
		} catch (Exception e) {

			Assertions.assertEquals(AccountValidatorError.INVALID_OBJECT_NULL, e.getMessage());
		}

	}

	@Test

	/**
	 * Test case for validating a valid account number.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */

	 void testvalidAccountNumber() throws ValidatorException {

		account.setAccountNumber("1234567890123456");

		Assertions.assertTrue(AccountValidator.validateAccountNumber(account.getAccountNumber()));
	}

	/**
	 * Test case for validating a null account number.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testNullAccountNumber() throws ValidatorException {
		try {
			AccountValidator.validateAccountNumber(null);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating an empty account number.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testEmptyAccountNumber() throws ValidatorException {
		try {
			AccountValidator.validateAccountNumber("");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating the length of an account number.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testLengthAccountNumber() throws ValidatorException {
		try {
			AccountValidator.validateAccountNumber("1234567890");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_LENGTH_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating the pattern of an account number.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testPatternAccountNumber() throws ValidatorException {
		try {
			AccountValidator.validateAccountNumber("1234567jhsdih890");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating a valid IFSC code.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testValidIfsc() throws ValidatorException {
		account.setIfsc("IDIB000K132");
		Assertions.assertTrue(AccountValidator.validateIfsc(account.getIfsc()));
	}

	/**
	 * Test case for validating a null IFSC code.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testNullIfsc() throws ValidatorException {
		try {
			AccountValidator.validateIfsc(null);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_NULL_IFSCCODE, e.getMessage());
		}
	}

	/**
	 * Test case for validating an empty IFSC code.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testEmptyIfsc() throws ValidatorException {
		try {
			AccountValidator.validateIfsc("");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_IFSCCODE, e.getMessage());
		}
	}

	/**
	 * Test case for validating the pattern of an IFSC code.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testPatternIfsc() throws ValidatorException {
		try {
			AccountValidator.validateIfsc("12345678901");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_IFSCCODE, e.getMessage());
		}
	}

	/**
	 * Test case for validating a valid phone number.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testValidPhoneNumber() throws ValidatorException {
		account.setPhoneNumber(9361320511L);
		Assertions.assertTrue(AccountValidator.validatePhoneNumber(account.getPhoneNumber()));
	}

	/**
	 * Test case for validating a valid minimum balance.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testValidMinimumBalance() throws ValidatorException {
		account.setMinimumBalance(1000.0);
		Assertions.assertTrue(AccountValidator.validateMinimumBalance(account.getMinimumBalance()));
	}

	/**
	 * Test case for validating an invalid minimum balance.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testInvalidMinimumBalance() throws ValidatorException {
		try {
			AccountValidator.validateMinimumBalance(10);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_MINIMUMBALANCE, e.getMessage());
		}
	}

	/**
	 * Test case for validating a null account type.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testNullType() throws ValidatorException {
		try {
			AccountValidator.validateType(null);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_NULL_TYPEOFACCOUNT, e.getMessage());
		}
	}

	/**
	 * Test case for validating a valid account type.
	 *
	 * @throws ValidatorException If an exception occurs during validation.
	 */
	@Test
	 void testValidType() throws ValidatorException {
		account.setCategory(AccountEnum.CURRENT);
		Assertions.assertTrue(AccountValidator.validateType(account.getCategory()));
	}
}
