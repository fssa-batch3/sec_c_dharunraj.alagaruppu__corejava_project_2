package com.fssa.netbliz.service;

import com.fssa.netbliz.dao.CustomerDAO;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.CustomerDAOException;
import com.fssa.netbliz.exception.CustomerValidatorException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.validator.CustomerValidator;

public class CustomerService {

	public static boolean addCustomer(Customer customer)
			throws CustomerDAOException, CustomerValidatorException, AccountValidatorException {

		if (CustomerValidator.validate(customer)) {

			return CustomerDAO.addCustomer(customer);
		}
		return false;
	}
}
