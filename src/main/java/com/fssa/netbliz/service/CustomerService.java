package com.fssa.netbliz.service;

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

	public boolean addCustomer(Customer customer) throws ServiceException {

		try {
			if (CustomerValidator.validate(customer) && !isAvailableAccount(customer.getPhoneNumber())) {

				return CustomerDAO.addCustomer(customer);
			}

		} catch (ValidatorException e) {

			e.getMessage();
			throw new ServiceException(e.getMessage());

		} catch (ServiceException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}

		catch (DAOException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}

		return false;
	}

	public boolean isActiveAccount(long phone) throws ServiceException {
		try {
			if (AccountValidator.validatePhoneNumber(phone)) {
				return CustomerDAO.isActiveAccount(phone);
			}

		} catch (ValidatorException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			e.getMessage();
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
			e.getMessage();
			throw new ServiceException(e.getMessage());

		}

		catch (DAOException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}

		return false;
	}

	public boolean logInCustomer(long phone, String email, String password) throws ServiceException {

		try {
			if (CustomerValidator.validateEmail(email) && CustomerValidator.validatePassword(password)
					&& AccountValidator.validatePhoneNumber(phone) && isActiveAccount(phone)
					&& isAvailableAccount(phone)) {

				return CustomerDAO.logInCustomer(phone, email, password);

			}
		} catch (ValidatorException e) {
			e.getMessage();

			throw new ServiceException(e.getMessage());
		}

		catch (ServiceException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		} catch (DAOException e) {
			e.getMessage();

			throw new ServiceException(e.getMessage());
		} catch (SQLException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}

		return false;
	}

	public List<Customer> getCustomerByPhoneNumber(long phone) throws ServiceException {

		try {
			if (AccountValidator.validatePhoneNumber(phone) && isActiveAccount(phone) && isAvailableAccount(phone)) {

				return CustomerDAO.getCustomerDetailsByPhoneNumber(phone);

			}
		} catch (ValidatorException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());

		} catch (ServiceException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}

		catch (DAOException e) {
			e.getMessage();
			throw new ServiceException(e.getMessage());
		}
		return null;
	}
}
