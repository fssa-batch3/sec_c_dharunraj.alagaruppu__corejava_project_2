package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;

 class TestCustomerService {

	CustomerService customerServive = new CustomerService();

	/**
	 * @throws DAOException       If there is an issue with the database operation
	 *                            during the test.
	 * @throws ValidatorException If there is an issue with validating the customer
	 *                            data during the test.
	 */
	@Test 
	 void testValidAddCustomer() {  

		Customer customer = new Customer("Vijay", "kumar", 7904433228l, "vijay@gmail.com", "1234567890Dh@",
				"1234567890Dh@");

		try {
			Assertions.assertTrue(customerServive.addCustomer(customer));
		} catch (ServiceException e) {
			Assertions.fail(e);
		} 
	}

	@Test

	 void testInvalidAddCustomer() {

		Customer customer = new Customer("Joel", "Premkumar", 7402473347l, "zoho@gmail.com", "740247Dh@3347",
				"740247Dh@3347");

		try {
			Assertions.assertFalse(customerServive.addCustomer(customer));
		} catch (ServiceException e) {
			Assertions.fail(e);
		}
	}

	@Test
	 void testValidationErrorAddCustomer() {

		Customer customer = new Customer("Joel", "", 790247334l, "vishalgmail.com", "74024747", "740247Dh@3347");

		Assertions.assertThrows(ServiceException.class, () -> customerServive.addCustomer(customer));

	}

	/**
	 * @throws DAOException       If there is an issue with the database operation
	 *                            during the test.
	 * @throws ValidatorException If there is an issue with validating the customer
	 *                            data during the test.
	 */
	@Test
	 void testValidLogInCustomer() {

		long phone = 9361320511l;
		String email = "dharun1@gmail.com";
		String password = "1234567890Dh@";

		try {
			Assertions.assertTrue(customerServive.logInCustomer(phone, email, password));
		} catch (ServiceException e) {
			e.getMessage();
		}
	}

	@Test

	 void testInvalidLogInCustomer() {

		long phone = 9361320511l;
		String email = "dhar@gmail.com";
		String password = "740247Dh@3347";

		try {
			Assertions.assertFalse(customerServive.logInCustomer(phone, email, password));
		} catch (ServiceException e) {
			e.getMessage();
		}
	}
}