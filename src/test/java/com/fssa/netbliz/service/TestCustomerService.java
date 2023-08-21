package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.exception.AccountDAOException;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.CustomerValidatorException;
import com.fssa.netbliz.exception.TransactionDAOException;
import com.fssa.netbliz.model.Customer;

public class TestCustomerService {

	@Test
	public void testValidCustomer() throws CustomerValidatorException, AccountValidatorException {

		Customer customer = new Customer("Balaji", "Arumugam", "8901234567", "balaji@gmail.com",
				"740247Dh@3347", "740247Dh@3347");

		Assertions.assertTrue(CustomerService.addCustomer(customer)); 
	}

}
