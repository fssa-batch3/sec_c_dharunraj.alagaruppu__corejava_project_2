package com.fssa.netbliz.service;

import java.rmi.ServerException;
import java.sql.SQLException;

import com.fssa.netbliz.dao.CustomerDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.CustomerValidator;

public class CustomerService {

	public static boolean addCustomer(Customer customer) throws ServerException {

		try {
			if (CustomerValidator.validate(customer)) {

				return CustomerDAO.addCustomer(customer);
			}
		} catch (ValidatorException e) {

			e.printStackTrace();
		} catch (DAOException e) {

			e.printStackTrace();
		}
		return false;
	}

	public static boolean logInCustomer(long phone, String email, String password) throws ServerException {

		try {

			if (CustomerValidator.validateEmail(email) && CustomerValidator.validatePassword(password)
					&& AccountValidator.validatePhoneNumber(phone)) {

				try {
					return CustomerDAO.logInCustomer(phone, email, password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (ValidatorException e) {

			e.printStackTrace();
		} catch (DAOException e) {

			e.printStackTrace();
		}

		return false;
	}
}
