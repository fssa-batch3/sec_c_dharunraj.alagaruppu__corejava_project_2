package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.CustomerDaoException;
import com.fssa.netbliz.exception.CustomerValidatorException;
import com.fssa.netbliz.model.Customer;

public class TestCustomerService {

	@Test
	public void testValidCustomer() throws CustomerDaoException, CustomerValidatorException, AccountValidatorException {

		Customer customer = new Customer("Dharunraj", "Alagaruppu", "9361320511", "dharun22@gmail.com", "1234567890Dh@",
				"1234567890Dh@");

		Assertions.assertTrue(CustomerService.addCustomer(customer));
	}  
}
