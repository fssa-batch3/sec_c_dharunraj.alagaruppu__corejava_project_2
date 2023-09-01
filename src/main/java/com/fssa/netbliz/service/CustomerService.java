package com.fssa.netbliz.service;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

import com.fssa.netbliz.dao.CustomerDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.CustomerValidator;

public class CustomerService {

	public boolean addCustomer(Customer customer) throws ServerException {

		try {
			if (CustomerValidator.validate(customer) && !isAvailableAccount(customer.getPhoneNumber())) {

				return CustomerDAO.addCustomer(customer); 
			}

		} catch (ValidatorException e) {

			e.printStackTrace();
		} catch (ServiceException e) {

			e.printStackTrace();
		} catch (DAOException e) {

			e.printStackTrace();
		}
		return false;
	}

	public boolean isActiveAccount(long phone) throws ServiceException {
		try {
			if (AccountValidator.validatePhoneNumber(phone)) {
				return CustomerDAO.isActiveAccount(phone);
			}

		} catch (ValidatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	public boolean isAvailableAccount(long phone) throws ServiceException {

		try {
			if (AccountValidator.validatePhoneNumber(phone)) {

				return CustomerDAO.isAvailableAccount(phone);
			}
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	public boolean logInCustomer(long phone, String email, String password) throws ServerException {

		try {
			if (CustomerValidator.validateEmail(email) && CustomerValidator.validatePassword(password)
					&& AccountValidator.validatePhoneNumber(phone) && isActiveAccount(phone)
					&& isAvailableAccount(phone)) {

				return CustomerDAO.logInCustomer(phone, email, password);

			}
		} catch (ValidatorException e) {

			e.printStackTrace();
		}

		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Customer> getCustomerByPhoneNumber(long phone) throws ServerException {

		try {
			if (AccountValidator.validatePhoneNumber(phone) && isActiveAccount(phone) && isAvailableAccount(phone)) {

				return CustomerDAO.getCustomerDetailsByPhoneNumber(phone);

			}
		} catch (ValidatorException e) {

			e.printStackTrace();
		}

		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {

			e.printStackTrace();
		}

		return null;
	}
}
