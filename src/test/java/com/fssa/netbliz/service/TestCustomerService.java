package com.fssa.netbliz.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.rmi.ServerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;

public class TestCustomerService {

	CustomerService customerServive = new CustomerService();

	/**
	 * Test case for validating the addition of a valid customer using the
	 * CustomerService's `addCustomer` method. It verifies whether a valid customer
	 * can be added successfully to the system.
	 *
	 * @throws DAOException       If there is an issue with the database operation
	 *                            during the test.
	 * @throws ValidatorException If there is an issue with validating the customer
	 *                            data during the test.
	 */
	@Test
	public void testValidAddCustomer() { 

		Customer customer = new Customer("Joel", "Premkumar", 7402473346l, "joel@gmail.com", "740247Dh@3347",
				"740247Dh@3347");

		try {
			Assertions.assertTrue(customerServive.addCustomer(customer));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	@Test

	public void testInvalidAddCustomer(){

		Customer customer = new Customer("Joel", "Premkumar", 7902413346l, "zoho@gmail.com", "740247Dh@3347",
				"740247Dh@3347");

		try {
			Assertions.assertFalse(customerServive.addCustomer(customer));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testValidationErrorAddCustomer() {

		Customer customer = new Customer("Joel", "", 790247334l, "vishalgmail.com", "74024747",
				"740247Dh@3347");

		assertThrows(ValidatorException.class, () -> customerServive.addCustomer(customer));

	}

	/**
	 * Test case for validating the successful login of a customer using the
	 * CustomerService's `logInCustomer` method. It verifies whether a customer with
	 * valid login credentials can log in successfully.
	 *
	 * @throws DAOException       If there is an issue with the database operation
	 *                            during the test.
	 * @throws ValidatorException If there is an issue with validating the customer
	 *                            data during the test.
	 */
	@Test
	public void testValidLogInCustomer(){

		long phone = 9361320511l;
		String email = "dharun1@gmail.com";
		String password = "1234567890Dh@";

		try {

			Assertions.assertTrue(customerServive.logInCustomer(phone, email, password));
		} catch (ServerException e) {

			e.printStackTrace();
		}
	}

	@Test

	public void testInvalidLogInCustomer() {

		long phone = 9361320511l;
		String email = "dhar@gmail.com";
		String password = "740247Dh@3347";

		try {
			Assertions.assertFalse(customerServive.logInCustomer(phone, email, password));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
}