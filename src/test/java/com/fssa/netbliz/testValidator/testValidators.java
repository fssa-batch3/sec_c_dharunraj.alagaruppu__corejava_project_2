package com.fssa.netbliz.testValidator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.accout.exception.validatorExceptions;
import com.fssa.netbliz.model.Account;
import com.fssa.netbliz.model.accountValidateErrors;
import com.fssa.netbliz.validator.AccountValidator;

public class testValidators {

	Account account = new Account();

	/* 
	 * This method is going to validate the object is not a null.. The value is set
	 * through the constructor of the object.. And this method is set the correct to
	 * the attributes..
	 * 
	 */

	@Test

	public void testValidObject() throws validatorExceptions { 

		Account account = new Account("1234567091123456", "IDIB000K132", "9361320511", 1000.0, "savings");

		Assertions.assertTrue(AccountValidator.validate(account));
	}

	@Test

	public void testInvalidObject() throws validatorExceptions {

		try {

			AccountValidator.validate(null);
		} catch (Exception e) {

			Assertions.assertEquals(accountValidateErrors.INVALID_OBJECT_NULL, e.getMessage());
		}

	}

	/*
	 * This method is going to validate the account number is valid.. Account number
	 * should be 16 digits of numbers.. And is doesn't contains any other
	 * characters..
	 */

	@Test

	public void testvalidAccountNumber() throws validatorExceptions {

		account.setAccountNumber("1234567890123456");

		Assertions.assertTrue(AccountValidator.validateAccountNumber(account.getAccountNumber()));
	}

	@Test
	public void testNullAccountNumber() throws validatorExceptions {

		try {
			AccountValidator.validateAccountNumber(null);

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_ACCOUNTNUMBER, e.getMessage());
		}

	}

	@Test
	public void testEmptyAccountNumber() throws validatorExceptions {

		try {
			AccountValidator.validateAccountNumber("");

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_EMPTY_ACCOUNTNUMBER, e.getMessage());
		}

	}

	@Test
	public void testLengthAccountNumber() throws validatorExceptions {

		try {
			AccountValidator.validateAccountNumber("1234567890");

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_LENGTH_ACCOUNTNUMBER, e.getMessage());
		}

	}

	@Test

	public void testpatternAccountNumber() throws validatorExceptions {

		try {

			AccountValidator.validateAccountNumber("1234567jhsdih890");
		} catch (Exception e) {

			Assertions.assertEquals(accountValidateErrors.INVALID_ACCOUNTNUMBER, e.getMessage());
		}
	}

	/*
	 * This method is going to validate the IFSC code is valid.. It is contains
	 * count of 11 numeric characters.. And It's value doesn't null
	 */

	@Test

	public void testvalidIfsc() throws validatorExceptions {

		account.setIfsc("IDIB000K132");

		Assertions.assertTrue(AccountValidator.validateIfsc(account.getIfsc()));
	}

	// This method is going to validate the IFSC Code is invalid..

	@Test
	public void testNullIfsc() throws validatorExceptions {

		try {
			AccountValidator.validateIfsc(null);

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_NULL_IFSCCODE, e.getMessage());
		}

	}

	@Test
	public void testEmptyIfsc() throws validatorExceptions {

		try {
			AccountValidator.validateIfsc("");

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_EMPTY_IFSCCODE, e.getMessage());
		}

	}

	@Test

	public void testpatternIfsc() throws validatorExceptions {

		try {

			AccountValidator.validateIfsc("12345678901");
		} catch (Exception e) {

			Assertions.assertEquals(accountValidateErrors.INVALID_IFSCCODE, e.getMessage());
		}
	}

	/*
	 * This method is going to validate the phone number is valid.. This method
	 * don't accept the empty value for user.. And It's doesn't have any other null
	 * values..
	 * 
	 */

	@Test

	public void testvalidPhoneNumber() throws validatorExceptions {

		account.setPhoneNumber("9361320511");

		Assertions.assertTrue(AccountValidator.validatePhoneNumber(account.getPhoneNumber()));
	}

	/*
	 * This method is going to validate the phone number is invalid..
	 */

	@Test
	public void testNullPhoneNumber() throws validatorExceptions {

		try {
			AccountValidator.validatePhoneNumber(null);

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_NULL_PHONENUMBER, e.getMessage());
		}

	}

	@Test
	public void testEmptyPhoneNumber() throws validatorExceptions {

		try {
			AccountValidator.validatePhoneNumber("");

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_EMPTY_PHONENUMBER, e.getMessage());
		}

	}

	@Test
	public void testLengthPhoneNumber() throws validatorExceptions {

		try {
			AccountValidator.validatePhoneNumber("123457890");

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_LENGTH_PHONENUMBER, e.getMessage());
		}

	}

	@Test

	public void testpatternPhoneNumber() throws validatorExceptions {

		try {

			AccountValidator.validatePhoneNumber("12345t7890");
		} catch (Exception e) {

			Assertions.assertEquals(accountValidateErrors.INVALID_PHONENUMBER, e.getMessage());
		}
	}

	@Test
	public void testNullType() throws validatorExceptions {

		try {
			AccountValidator.validateType(null);

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_NULL_TYPEOFACCOUNT, e.getMessage());
		}

	}

	@Test
	public void testEmptyType() throws validatorExceptions {

		try {
			AccountValidator.validateType("");

		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_EMPTY_TYPEOFACCOUNT, e.getMessage());
		}

	}

	/*
	 * This method is going to validate the Minimum balance is valid.. The minimum
	 * balance should be greater than 500 rupee and less than 25000 rupee
	 */

	@Test
	public void testValidMinimumBalance() throws validatorExceptions {

		account.setMinimumBalance(1000.0);
		Assertions.assertTrue(AccountValidator.validateMinimumBalance(account.getMinimumBalance()));

	}

	// This method is check the minimum balance is invalid..

	@Test
	public void testInvalidMinimumBalance() throws validatorExceptions {

		try {

			AccountValidator.validateMinimumBalance(10);
		} catch (Exception e) {
			Assertions.assertEquals(accountValidateErrors.INVALID_MINIMUMBALANCE, e.getMessage());
		}
	}

	@Test
	public void testValidType() throws validatorExceptions {

		account.setCategory("SAVINGS");
		Assertions.assertTrue(AccountValidator.validateType(account.getCategory()));
	}

	@Test

	public void testInvalidType() throws validatorExceptions {

		try {

			account.setCategory("SALARYACCOUNT");
			AccountValidator.validateType(account.getCategory());

		} catch (Exception e) {

			Assertions.assertEquals(accountValidateErrors.INVALID_TYPEOFACCOUNT, e.getMessage());
		}
	}

	/*
	 * This method is going to validate the date of linked account in the app is
	 * valid.. This is just set the local date through the setter method.. It's only
	 * for read the account is active or non-active..
	 */
//	@Test
//	public void testDate() {
//		account.setDateOfjoining();
//		LocalDate nowDate = account.getDateOfjoining();
//		Assertions.assertEquals(nowDate, LocalDate.now());
//	}

	/*
	 * This method is going to validate the enum variable that is present in the
	 * module object.. Savings account and current account thus are the account only
	 * available in the application
	 */
}
