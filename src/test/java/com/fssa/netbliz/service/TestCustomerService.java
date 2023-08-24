package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;

public class TestCustomerService {

	@Test
	public void testValidCustomer() throws DAOException, ValidatorException {

		Customer customer = new Customer("Mathan", "Raj", "7402473346", "mathan@gmail.com", "740247Dh@3347",
				"740247Dh@3347");

		Assertions.assertTrue(CustomerService.addCustomer(customer));
	}

}