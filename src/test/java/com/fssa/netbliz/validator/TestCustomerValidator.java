package com.fssa.netbliz.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.error.AccountValidatorError;
import com.fssa.netbliz.error.CustomerValidatorError;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.CustomerValidatorException;
import com.fssa.netbliz.model.Customer;

public class TestCustomerValidator {

	Customer customer = new Customer();

	@Test

	public void testValidate() throws CustomerValidatorException, AccountValidatorException {

		Customer customer = new Customer("Dharunraj", "Alagaruppu", "9361320511", "dharun@gmail.com", "1234567890Dh@",
				"1234567890Dh@");

		Assertions.assertTrue(CustomerValidator.validate(customer));
	}

	@Test

	public void testInvalidObject() throws CustomerValidatorException {

		try {

			CustomerValidator.validate(null);
		} catch (Exception e) {

			Assertions.assertEquals(CustomerValidatorError.NULL_OBJECT, e.getMessage());
		}

	}

	@Test

	public void testValidFirstName() throws CustomerValidatorException {

		customer.setfName("Dharunraj");

		Assertions.assertTrue(CustomerValidator.validateFirstName(customer.getfName()));
	}

	@Test
	public void testNullFirstName() throws CustomerValidatorException {

		try {
			CustomerValidator.validateFirstName(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}

	}

	@Test
	public void testEmptyFirstName() throws CustomerValidatorException {

		try {
			CustomerValidator.validateFirstName("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}

	}

	@Test

	public void testpatternFirstName() throws CustomerValidatorException {

		try {

			CustomerValidator.validateFirstName("RDX100");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}
	}

	@Test

	public void testLengthOfFirstName() throws CustomerValidatorException {

		try {

			CustomerValidator.validateFirstName("qwertyuiopasdfghjklzxcvbnmqwertyuio");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}
	}

	@Test

	public void testValidLastName() throws CustomerValidatorException {

		customer.setlName("Alagaruppu");

		Assertions.assertTrue(CustomerValidator.validateLastName(customer.getlName()));
	}

	@Test
	public void testNullLastName() throws CustomerValidatorException {

		try {
			CustomerValidator.validateLastName(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}

	}

	@Test
	public void testEmptyLastName() throws CustomerValidatorException {

		try {
			CustomerValidator.validateLastName("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}

	}

	@Test

	public void testpatternLastName() throws CustomerValidatorException {

		try {

			CustomerValidator.validateLastName("RDX100");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}
	}

	@Test

	public void testLengthOfLastName() throws CustomerValidatorException {

		try {

			CustomerValidator.validateLastName("qwertyuiopasdfghjklzxcvbnmqwertyuio");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_NAME, e.getMessage());
		}
	}

	@Test

	public void testValidEmail() throws CustomerValidatorException {

		customer.setEmail("dharun@gmail.com");

		Assertions.assertTrue(CustomerValidator.validateEmail(customer.getEmail()));
	}

	@Test
	public void testNullEmail() throws CustomerValidatorException {

		try {
			CustomerValidator.validateEmail(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_EMAIL, e.getMessage());
		}

	}

	@Test
	public void testEmptyEmail() throws CustomerValidatorException {

		try {
			CustomerValidator.validateEmail("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_EMAIL, e.getMessage());
		}

	}

	@Test

	public void testpatternEmail() throws CustomerValidatorException {

		try {

			CustomerValidator.validateEmail("dharungmail");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_EMAIL, e.getMessage());
		}
	}

	@Test

	public void testValidPassword() throws CustomerValidatorException {

		customer.setPassword("1234567890Dh@");

		Assertions.assertTrue(CustomerValidator.validatePassword(customer.getPassword()));
	}

	@Test
	public void testNullPassword() throws CustomerValidatorException {

		try {
			CustomerValidator.validatePassword(null);

		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_PASSWORD, e.getMessage());
		}

	}

	@Test
	public void testEmptyPassword() throws CustomerValidatorException {

		try {
			CustomerValidator.validatePassword("");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_PASSWORD, e.getMessage());
		}

	}

	@Test

	public void testpatternPassword() throws CustomerValidatorException {

		try {

			CustomerValidator.validatePassword("dharungmail");
		} catch (Exception e) {
			Assertions.assertEquals(CustomerValidatorError.INVALID_PASSWORD, e.getMessage());
		}
	}

	@Test

	public void testNullComparePassword() throws CustomerValidatorException {

		try {

			CustomerValidator.validateComparePassword(null, null);
		} catch (Exception e) {

			Assertions.assertEquals(CustomerValidatorError.INVALID_PASSWORD, e.getMessage());
		}
	}

	@Test

	public void testEmptyComparePassword() throws CustomerValidatorException {

		try {

			CustomerValidator.validateComparePassword("", "");
		} catch (Exception e) {

			Assertions.assertEquals(CustomerValidatorError.INVALID_PASSWORD, e.getMessage());
		}
	}

	@Test

	public void testInvalidComparePassword() throws CustomerValidatorException {

		try {

			CustomerValidator.validateComparePassword("dharunraj123", "dharunraj123");
		} catch (Exception e) {

			Assertions.assertEquals(CustomerValidatorError.INVALID_PASSWORD, e.getMessage());
		}
	}

}
