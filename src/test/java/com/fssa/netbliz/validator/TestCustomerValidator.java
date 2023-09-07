package com.fssa.netbliz.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.netbliz.error.CustomerValidatorError;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;

 class TestCustomerValidator {

	Customer customer = new Customer();

	@Test

	 void testValidate() throws ValidatorException {

		Customer customer = new Customer("Aravind", "Ram", 9080668509l, "aravind@gmail.com", "1234567890Dh@", "1234567890Dh@");

		Assertions.assertTrue(CustomerValidator.validate(customer));
	} 

	@Test

	 void testInvalidObject() throws ValidatorException {

		try {

			CustomerValidator.validate(null);
		} catch (Exception e) {

			Assertions.assertEquals(CustomerValidatorError.NULL_OBJECT, e.getMessage());
		}

	}

	@Test

	 void testValidFirstName() throws ValidatorException {

		customer.setFirstName("Dharunraj");

		Assertions.assertTrue(CustomerValidator.validateFirstName(customer.getFirstName()));
	}

	@Test
	 void testNullFirstName() throws ValidatorException {

		try {
			CustomerValidator.validateFirstName(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NULL_FIRST_NAME, e.getMessage());
		}

	}

	@Test
	 void testEmptyFirstName() throws ValidatorException {

		try {
			CustomerValidator.validateFirstName("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_EMPTY_FIRST_NAME, e.getMessage());
		}

	}

	@Test

	 void testpatternFirstName() throws ValidatorException {

		try {

			CustomerValidator.validateFirstName("RDX100");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}
	}

	@Test

	 void testLengthOfFirstName() throws ValidatorException {

		try {

			CustomerValidator.validateFirstName("qwertyuiopasdfghjklzxcvbnmqwertyuio");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_LENGTH_FIRST_NAME, e.getMessage());
		}
	}

	@Test

	 void testValidLastName() throws ValidatorException {

		customer.setLastName("Alagaruppu");

		Assertions.assertTrue(CustomerValidator.validateLastName(customer.getLastName()));
	}

	@Test
	 void testNullLastName() throws ValidatorException {

		try {
			CustomerValidator.validateLastName(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NULL_LAST_NAME, e.getMessage());
		}

	}

	@Test
	 void testEmptyLastName() throws ValidatorException {

		try {
			CustomerValidator.validateLastName("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_EMPTY_LAST_NAME, e.getMessage());
		}

	}

	@Test

	 void testpatternLastName() throws ValidatorException {

		try {

			CustomerValidator.validateLastName("RDX100");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}
	}

	@Test

	 void testLengthOfLastName() throws ValidatorException {

		try {

			CustomerValidator.validateLastName("qwertyuiopasdfghjklzxcvbnmqwertyuio");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_LENGTH_LAST_NAME, e.getMessage());
		}
	}

	@Test

	 void testValidEmail() throws ValidatorException {

		customer.setEmail("dharun@gmail.com");

		Assertions.assertTrue(CustomerValidator.validateEmail(customer.getEmail()));
	}

	@Test
	 void testNullEmail() throws ValidatorException {

		try {
			CustomerValidator.validateEmail(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NULL_EMAIL, e.getMessage());
		}

	}

	@Test
	 void testEmptyEmail() throws ValidatorException {

		try {
			CustomerValidator.validateEmail("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_EMPTY_EMAIL, e.getMessage());
		}

	}

	@Test

	 void testpatternEmail() throws ValidatorException {

		try {

			CustomerValidator.validateEmail("dharun@gmail.com");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_PATTERN_EMAIL, e.getMessage());
		} 
	}
 
	@Test

	 void testValidPassword() throws ValidatorException {

		customer.setPassword("1234567890Dh@");
 
		Assertions.assertTrue(CustomerValidator.validatePassword(customer.getPassword()));
	}

	@Test
	 void testNullPassword() throws ValidatorException {

		try {
			CustomerValidator.validatePassword(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NULL_PASSWORD, e.getMessage());
		}

	}

	@Test
	 void testEmptyPassword() throws ValidatorException {

		try {
			CustomerValidator.validatePassword("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_EMPTY_PASSWORD, e.getMessage());
		}

	}

	@Test

	 void testpatternPassword() throws ValidatorException {

		try {

			CustomerValidator.validatePassword("Dharunraj@123");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_PASSWORD, e.getMessage());
		}
	}

	@Test

	 void testNullComparePassword() throws ValidatorException {

		try {

			CustomerValidator.validateComparePassword(null, null);
		} catch (Exception e) {

			Assertions.assertEquals(CustomerValidatorError.INVALID_NULL_PASSWORD, e.getMessage());
		}
	}

	@Test

	 void testEmptyComparePassword() throws ValidatorException {

		try {

			CustomerValidator.validateComparePassword("", "");
		} catch (Exception e) {

			Assertions.assertEquals(CustomerValidatorError.INVALID_EMPTY_PASSWORD, e.getMessage());
		}
	}

	@Test

	 void testInvalidComparePassword() throws ValidatorException {

		try {

			CustomerValidator.validateComparePassword("Dharunraj@123", "Dharunraj@124");
		} catch (Exception e) {

			Assertions.assertEquals(CustomerValidatorError.WRONG_PASSWORD, e.getMessage());
		}
	}

	@Test
	 void testValidCustomerId() {

		try {
			Assertions.assertTrue(CustomerValidator.validateCustomerId(123));
		} catch (ValidatorException e) {
			Assertions.fail("Validation of valid customer ID should not throw an exception.");
		}
	}

	@Test
	 void testInvalidCustomerId() {

		try {
			Assertions.assertFalse(CustomerValidator.validateCustomerId(-1));
			Assertions.fail("Validation of invalid customer ID should throw a ValidatorException.");
		} catch (ValidatorException e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_CUSTOMER_ID, e.getMessage());
		}
	}

}
