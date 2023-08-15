package com.fssa.netbliz.service;

import com.fssa.netbliz.dao.CustomerDao;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.CustomerDaoException;
import com.fssa.netbliz.exception.CustomerValidatorException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.validator.CustomerValidator;

public class CustomerService {

	public static boolean addCustomer(Customer customer) throws CustomerDaoException, CustomerValidatorException, AccountValidatorException {

		if(CustomerValidator.validate(customer)) {
			
			return CustomerDao.addCustomer(customer);
		}
		return false;
	}
}
