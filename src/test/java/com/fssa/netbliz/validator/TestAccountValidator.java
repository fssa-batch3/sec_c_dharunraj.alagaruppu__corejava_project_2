package com.fssa.netbliz.validator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.netbliz.error.AccountValidatorError;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.model.Account;

public class TestAccountValidator {

	Account account = new Account();

	/**
	 * This method is going to validate the object is not a null.. The value is set
	 * through the constructor of the object.. And this method is set the correct to
	 * the attributes..
	 *  
	 */

	@Test

	public void testValidObject() throws AccountValidatorException {

		Account account = new Account("1234567091123456", "IDIB000K132", "9361320511", 1000.0, "savings");

		Assertions.assertTrue(AccountValidator.validate(account));
	}

	@Test

	public void testInvalidObject() throws AccountValidatorException {

		try {

			AccountValidator.validate(null);
		} catch (Exception e) {

			Assertions.assertEquals(AccountValidatorError.INVALID_OBJECT_NULL, e.getMessage());
		}

	} 

	/**
	 * 
	 * This method is going to validate the account number is valid.. Account number
	 * should be 16 digits of numbers.. And is doesn't contains any other
	 * characters..
	 */

	@Test

	public void testvalidAccountNumber() throws AccountValidatorException {

		account.setAccountNumber("1234567890123456");

		Assertions.assertTrue(AccountValidator.validateAccountNumber(account.getAccountNumber()));
	}

	@Test
	public void testNullAccountNumber() throws AccountValidatorException {

		try {
			AccountValidator.validateAccountNumber(null);

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_ACCOUNTNUMBER, e.getMessage());
		}

	}

	@Test
	public void testEmptyAccountNumber() throws AccountValidatorException {

		try {
			AccountValidator.validateAccountNumber("");

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_ACCOUNTNUMBER, e.getMessage());
		}

	}

	@Test
	public void testLengthAccountNumber() throws AccountValidatorException {

		try {
			AccountValidator.validateAccountNumber("1234567890");

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_LENGTH_ACCOUNTNUMBER, e.getMessage());
		}

	} 

	@Test

	public void testpatternAccountNumber() throws AccountValidatorException {

		try {

			AccountValidator.validateAccountNumber("1234567jhsdih890");
		} catch (Exception e) {

			Assertions.assertEquals(AccountValidatorError.INVALID_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/***
	 * This method is going to validate the IFSC code is valid.. It is contains
	 * count of 11 numeric characters.. And It's value doesn't null
	 */

	@Test

	public void testvalidIfsc() throws AccountValidatorException {

		account.setIfsc("IDIB000K132");

		Assertions.assertTrue(AccountValidator.validateIfsc(account.getIfsc()));
	}

	// This method is going to validate the IFSC Code is invalid..

	@Test
	public void testNullIfsc() throws AccountValidatorException {

		try {
			AccountValidator.validateIfsc(null);

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_NULL_IFSCCODE, e.getMessage());
		}

	}

	@Test
	public void testEmptyIfsc() throws AccountValidatorException {

		try {
			AccountValidator.validateIfsc("");

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_IFSCCODE, e.getMessage());
		}

	}

	@Test

	public void testpatternIfsc() throws AccountValidatorException {

		try {

			AccountValidator.validateIfsc("12345678901");
		} catch (Exception e) {

			Assertions.assertEquals(AccountValidatorError.INVALID_IFSCCODE, e.getMessage());
		}
	}

	/**
	 * This method is going to validate the phone number is valid.. This method
	 * don't accept the empty value for user.. And It's doesn't have any other null
	 * values..
	 * 
	 */

	@Test

	public void testvalidPhoneNumber() throws AccountValidatorException {

		account.setPhoneNumber("9361320511");

		Assertions.assertTrue(AccountValidator.validatePhoneNumber(account.getPhoneNumber()));
	}

	/*
	 * This method is going to validate the phone number is invalid..
	 */

	@Test
	public void testNullPhoneNumber() throws AccountValidatorException {

		try {
			AccountValidator.validatePhoneNumber(null);

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_NULL_PHONENUMBER, e.getMessage());
		}

	}

	@Test
	public void testEmptyPhoneNumber() throws AccountValidatorException {

		try {
			AccountValidator.validatePhoneNumber("");

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_PHONENUMBER, e.getMessage());
		}

	}

	@Test
	public void testLengthPhoneNumber() throws AccountValidatorException {

		try {
			AccountValidator.validatePhoneNumber("123457890");

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_LENGTH_PHONENUMBER, e.getMessage());
		}

	}

	@Test

	public void testpatternPhoneNumber() throws AccountValidatorException {

		try {

			AccountValidator.validatePhoneNumber("12345t7890");
		} catch (Exception e) {

			Assertions.assertEquals(AccountValidatorError.INVALID_PHONENUMBER, e.getMessage());
		}
	}

	@Test
	public void testNullType() throws AccountValidatorException {

		try {
			AccountValidator.validateType(null);

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_NULL_TYPEOFACCOUNT, e.getMessage());
		}

	}

	@Test
	public void testEmptyType() throws AccountValidatorException {

		try {
			AccountValidator.validateType("");

		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_EMPTY_TYPEOFACCOUNT, e.getMessage());
		}

	}

	/**
	 * This method is going to validate the Minimum balance is valid.. The minimum
	 * balance should be greater than 500 rupee and less than 25000 rupee
	 */

	@Test
	public void testValidMinimumBalance() throws AccountValidatorException {

		account.setMinimumBalance(1000.0);
		Assertions.assertTrue(AccountValidator.validateMinimumBalance(account.getMinimumBalance()));

	}

	// This method is check the minimum balance is invalid..

	@Test
	public void testInvalidMinimumBalance() throws AccountValidatorException {

		try {

			AccountValidator.validateMinimumBalance(10);
		} catch (Exception e) {
			Assertions.assertEquals(AccountValidatorError.INVALID_MINIMUMBALANCE, e.getMessage());
		}
	}

	@Test
	public void testValidType() throws AccountValidatorException {

		account.setCategory("SAVINGS");
		Assertions.assertTrue(AccountValidator.validateType(account.getCategory()));
	}

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
