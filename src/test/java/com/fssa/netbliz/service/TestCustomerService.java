package com.fssa.netbliz.service;

import java.rmi.ServerException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;

public class TestCustomerService {

	@Test

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

	public void testValidCustomer() throws DAOException, ValidatorException {

		Customer customer = new Customer("Joel", "Premkumar", 7402473346l, "joel@gmail.com", "740247Dh@3347",
				"740247Dh@3347");  

		try {
			Assertions.assertTrue(CustomerService.addCustomer(customer));
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
 
	@Test

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

	public void testValidLogInCustomer() throws DAOException, ValidatorException {

		long phone = 9361320511l;
		String email = "dharun@gmail.com";
		String password = "740247Dh@3347";

		try {

			Assertions.assertTrue(CustomerService.logInCustomer(phone, email, password));
		} catch (ServerException e) {

			e.printStackTrace();
		}
	}

}