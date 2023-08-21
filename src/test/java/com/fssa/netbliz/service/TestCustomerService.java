package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.dao.Logger;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.CustomerDAOException;
import com.fssa.netbliz.exception.CustomerValidatorException;
import com.fssa.netbliz.model.Customer;

public class TestCustomerService {

	@Test
	public void testValidCustomer() throws CustomerDAOException, CustomerValidatorException, AccountValidatorException {

		Customer customer = new Customer("Naresh", "Kumar", "7890123456", "naresh@gmail.com",
				"740247Dh@3347", "740247Dh@3347");

		Assertions.assertTrue(CustomerService.addCustomer(customer));
		Logger.info("Welcome to NETBLIZ"); 
	}

}
