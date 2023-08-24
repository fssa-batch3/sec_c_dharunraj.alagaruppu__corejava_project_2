package com.fssa.netbliz.service;

import com.fssa.netbliz.dao.CustomerDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.validator.CustomerValidator;

public class CustomerService {

	public static boolean addCustomer(Customer customer) throws ValidatorException, DAOException {

		if (CustomerValidator.validate(customer)) {

			return CustomerDAO.addCustomer(customer);
		}
		return false;
	}
}
