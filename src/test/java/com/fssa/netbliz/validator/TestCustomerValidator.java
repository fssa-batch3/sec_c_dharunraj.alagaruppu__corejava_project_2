package com.fssa.netbliz.validator;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import com.fssa.netbliz.error.CustomerValidatorError;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;

 class TestCustomerValidator {

	Customer customer = new Customer(); 

	 /**
     * Test case for validating a complete Customer object.
     * 
     * It checks if a complete Customer object is validated successfully.
     * The test expects the method to return true.
     */
	
	@Test

	 void testValidate() throws ValidatorException {

		Customer customer = new Customer("Aravind", "Ram", 9080668509l, "aravind@gmail.com", "1234567890Dh@", "1234567890Dh@");

		Assertions.assertTrue(CustomerValidator.validate(customer));
	} 
	
	/**
     * Test case for validating a null object.
     * 
     * It checks if a null Customer object is properly handled.
     * The test expects a ValidatorException with the error message for a null object.
     */
	

	@Test

	 void testInvalidObject() throws ValidatorException {

		try {

			CustomerValidator.validate(null);
		} catch (Exception e) {

			Assertions.assertEquals(CustomerValidatorError.NULL_OBJECT, e.getMessage());
		}

	}
	 /**
     * Test case for validating a valid first name.
     * 
     * It checks if a valid first name is properly validated.
     * The test expects the method to return true.
     */
	
	@Test

	 void testValidFirstName() throws ValidatorException {

		customer.setFirstName("Dharunraj");

		Assertions.assertTrue(CustomerValidator.validateFirstName(customer.getFirstName()));
	}
	
	/**
     * Test case for validating a null first name.
     * 
     * It checks if a null first name is properly handled.
     * The test expects a ValidatorException with the error message for a null first name.
     */

	@Test
	 void testNullFirstName() throws ValidatorException {

		try {
			CustomerValidator.validateFirstName(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NULL_FIRST_NAME, e.getMessage());
		}

	}
	
	 /**
     * Test case for validating an empty first name.
     * 
     * It checks if an empty first name is properly handled.
     * The test expects a ValidatorException with the error message for an empty first name.
     */

	@Test
	 void testEmptyFirstName() throws ValidatorException {

		try {
			CustomerValidator.validateFirstName("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_EMPTY_FIRST_NAME, e.getMessage());
		}

	}
	
	/**
     * Test case for validating a first name with an invalid pattern.
     * 
     * It checks if a first name with an invalid pattern is properly handled.
     * The test expects a ValidatorException with the error message for an invalid first name.
     */

	@Test

	 void testpatternFirstName() throws ValidatorException {

		try {

			CustomerValidator.validateFirstName("RDX100");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}
	}
	 /**
     * Test the validation of the length of the first name.
     *
     * @throws ValidatorException If the first name length is invalid.
     */
	@Test

	 void testLengthOfFirstName() throws ValidatorException {

		try {

			CustomerValidator.validateFirstName("qwertyuiopasdfghjklzxcvbnmqwertyuio");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_LENGTH_FIRST_NAME, e.getMessage());
		}
	}
	 /**
     * Test the validation of a valid last name.
     *
     * @throws ValidatorException If the last name is invalid.
     */
	@Test

	 void testValidLastName() throws ValidatorException {

		customer.setLastName("Alagaruppu");

		Assertions.assertTrue(CustomerValidator.validateLastName(customer.getLastName()));
	}
	  /**
     * Test the validation of a null last name.
     *
     * @throws ValidatorException If the last name is null.
     */
	@Test
	 void testNullLastName() throws ValidatorException {

		try {
			CustomerValidator.validateLastName(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NULL_LAST_NAME, e.getMessage());
		}

	}
	/**
     * Test the validation of an empty last name.
     *
     * @throws ValidatorException If the last name is empty.
     */
	@Test
	 void testEmptyLastName() throws ValidatorException {

		try {
			CustomerValidator.validateLastName("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_EMPTY_LAST_NAME, e.getMessage());
		}

	}
	 /**
     * Test the validation of the pattern of the last name.
     *
     * @throws ValidatorException If the last name pattern is invalid.
     */
	@Test

	 void testpatternLastName() throws ValidatorException {

		try {

			CustomerValidator.validateLastName("RDX100");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}
	}
	 /**
     * Test the validation of the length of the last name.
     *
     * @throws ValidatorException If the last name length is invalid.
     */
	@Test

	 void testLengthOfLastName() throws ValidatorException {

		try {

			CustomerValidator.validateLastName("qwertyuiopasdfghjklzxcvbnmqwertyuio");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_LENGTH_LAST_NAME, e.getMessage());
		}
	}
	// (Repeat similar JavaDoc comments for other test methods...)

    /**
     * Test the validation of a valid customer ID.
     */
	@Test

	 void testValidEmail() throws ValidatorException {

		customer.setEmail("dharun@gmail.com");

		Assertions.assertTrue(CustomerValidator.validateEmail(customer.getEmail()));
	}

    /**
     * Test the validation of an invalid customer ID.
     */
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
	/**
     * Test the validation of a valid customer ID.
     */
	@Test
	 void testValidCustomerId() {

		try {
			Assertions.assertTrue(CustomerValidator.validateCustomerId(123));
		} catch (ValidatorException e) {
			Assertions.fail("Validation of valid customer ID should not throw an exception.");
		}
	}
	 /**
     * Test the validation of an invalid customer ID.
     */
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
