package com.fssa.netbliz.validator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.netbliz.error.AccountValidatorError;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.model.Account;

/**
 * A class containing test cases for the AccountValidator class.
 */

public class TestAccountValidator {

	Account account = new Account();

	@Test

	/**
	 * Test case for validating a valid Account object.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */

	public void testValidObject() throws AccountValidatorException {

		Account account = new Account("1234567091123456", "IDIB000K132", "9361320511", 1000.0, "savings");

		Assertions.assertTrue(AccountValidator.validate(account));
	}

	@Test

	/**
	 * Test case for validating an invalid (null) Account object.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */

	public void testInvalidObject() throws AccountValidatorException {

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
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */

	public void testvalidAccountNumber() throws AccountValidatorException {

		account.setAccountNumber("1234567890123456");

		Assertions.assertTrue(AccountValidator.validateAccountNumber(account.getAccountNumber()));
	}

	/**
	 * Test case for validating a null account number.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testNullAccountNumber() throws AccountValidatorException {
		try {
			AccountValidator.validateAccountNumber(null);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating an empty account number.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testEmptyAccountNumber() throws AccountValidatorException {
		try {
			AccountValidator.validateAccountNumber("");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating the length of an account number.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testLengthAccountNumber() throws AccountValidatorException {
		try {
			AccountValidator.validateAccountNumber("1234567890");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_LENGTH_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating the pattern of an account number.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testPatternAccountNumber() throws AccountValidatorException {
		try {
			AccountValidator.validateAccountNumber("1234567jhsdih890");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating a valid IFSC code.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testValidIfsc() throws AccountValidatorException {
		account.setIfsc("IDIB000K132");
		Assertions.assertTrue(AccountValidator.validateIfsc(account.getIfsc()));
	}

	/**
	 * Test case for validating a null IFSC code.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testNullIfsc() throws AccountValidatorException {
		try {
			AccountValidator.validateIfsc(null);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_NULL_IFSCCODE, e.getMessage());
		}
	}

	/**
	 * Test case for validating an empty IFSC code.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testEmptyIfsc() throws AccountValidatorException {
		try {
			AccountValidator.validateIfsc("");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_IFSCCODE, e.getMessage());
		}
	}

	/**
	 * Test case for validating the pattern of an IFSC code.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testPatternIfsc() throws AccountValidatorException {
		try {
			AccountValidator.validateIfsc("12345678901");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_IFSCCODE, e.getMessage());
		}
	}

	/**
	 * Test case for validating a valid phone number.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testValidPhoneNumber() throws AccountValidatorException {
		account.setPhoneNumber("9361320511");
		Assertions.assertTrue(AccountValidator.validatePhoneNumber(account.getPhoneNumber()));
	}

	/**
	 * Test case for validating a null phone number.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testNullPhoneNumber() throws AccountValidatorException {
		try {
			AccountValidator.validatePhoneNumber(null);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_NULL_PHONENUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating an empty phone number.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testEmptyPhoneNumber() throws AccountValidatorException {
		try {
			AccountValidator.validatePhoneNumber("");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_PHONENUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating the length of a phone number.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testLengthPhoneNumber() throws AccountValidatorException {
		try {
			AccountValidator.validatePhoneNumber("123457890");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_LENGTH_PHONENUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating the pattern of a phone number.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testPatternPhoneNumber() throws AccountValidatorException {
		try {
			AccountValidator.validatePhoneNumber("12345t7890");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_PHONENUMBER, e.getMessage());
		}
	}

	/**
	 * Test case for validating a null account type.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testNullType() throws AccountValidatorException {
		try {
			AccountValidator.validateType(null);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_NULL_TYPEOFACCOUNT, e.getMessage());
		}
	}

	/**
	 * Test case for validating an empty account type.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testEmptyType() throws AccountValidatorException {
		try {
			AccountValidator.validateType("");
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_TYPEOFACCOUNT, e.getMessage());
		}
	}

	/**
	 * Test case for validating a valid minimum balance.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testValidMinimumBalance() throws AccountValidatorException {
		account.setMinimumBalance(1000.0);
		Assertions.assertTrue(AccountValidator.validateMinimumBalance(account.getMinimumBalance()));
	}

	/**
	 * Test case for validating an invalid minimum balance.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testInvalidMinimumBalance() throws AccountValidatorException {
		try {
			AccountValidator.validateMinimumBalance(10);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_MINIMUMBALANCE, e.getMessage());
		}
	}

	/**
	 * Test case for validating a valid account type.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testValidType() throws AccountValidatorException {
		account.setCategory("SAVINGS");
		Assertions.assertTrue(AccountValidator.validateType(account.getCategory()));
	}

	/**
	 * Test case for validating an invalid account type.
	 *
	 * @throws AccountValidatorException If an exception occurs during validation.
	 */
	@Test
	public void testInvalidType() throws AccountValidatorException {
		try {
			account.setCategory("SALARY");
			AccountValidator.validateType(account.getCategory());
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_TYPEOFACCOUNT, e.getMessage());
		}
	}

}
