package com.fssa.netbliz.service;

import java.sql.SQLException;

import com.fssa.netbliz.dao.CustomerDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.CustomerValidator;

public class CustomerService {

	/**
	 * Adds a customer to the system if the provided customer data is valid and the
	 * phone number is not already registered.
	 *
	 * @param customer The Customer object to add.
	 * @return True if the customer was added successfully, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean addCustomer(Customer customer) throws ServiceException {

		try {
			if (CustomerValidator.validate(customer) && !isAvailableAccount(customer.getPhoneNumber())) {
				return CustomerDAO.addCustomer(customer);
			} else {
				throw new ServiceException("Mobile Number Already registered with another account");
			}
		} catch (ValidatorException | ServiceException | DAOException e) {
			e.getMessage();
		}
		throw new ServiceException("Customer details wrong");

	}

	/**
	 * Checks if an account with the given phone number is active.
	 *
	 * @param phone The phone number to check.
	 * @return True if the account is active, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean isActiveAccount(long phone) throws ServiceException {

		try {
			if (AccountValidator.validatePhoneNumber(phone)) {
				return CustomerDAO.isActiveAccount(phone);
			} else {
				throw new ServiceException("Your account is not active");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {
			e.getMessage();
		}
		return false;

	}

	/**
	 * Checks if an account with the given phone number is available.
	 *
	 * @param phone The phone number to check.
	 * @return True if the account is available, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean isAvailableAccount(long phone) throws ServiceException {

		try {
			if (AccountValidator.validatePhoneNumber(phone)) {
				return CustomerDAO.isAvailableAccount(phone);
			} else {
				throw new ServiceException("Your account is not available");
			}
		} catch (ValidatorException | DAOException | ServiceException e) {
			e.getMessage();
		}
		return false;

	}

	/**
	 * Logs in a customer using their phone number and password if the provided
	 * credentials are valid.
	 *
	 * @param phone    The customer's phone number.
	 * @param password The customer's password.
	 * @return True if the login is successful, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean logInCustomer(long phone, String password) throws ServiceException {

		try {
			if (CustomerValidator.validatePassword(password) && AccountValidator.validatePhoneNumber(phone)
					&& isActiveAccount(phone) && isAvailableAccount(phone)) {
				return CustomerDAO.logInCustomer(phone, password);
			}
		} catch (ValidatorException | ServiceException | DAOException | SQLException e) {

			e.getMessage();
		}
		throw new ServiceException("Try again with your valid phone number and other fields");
	}

	/**
	 * Retrieves customer details by phone number if the phone number is valid, the
	 * account is active, and the account is available.
	 *
	 * @param phone The phone number to search for.
	 * @return The Customer object representing customer details, or null if not
	 *         found.
	 * @throws ServiceException If there is a service-level error.
	 */

	public Customer getCustomerByPhoneNumber(long phone) throws ServiceException {

		Customer customer = new Customer();

		try {
			if (AccountValidator.validatePhoneNumber(phone) && isActiveAccount(phone) && isAvailableAccount(phone)) {

				customer = CustomerDAO.getCustomerDetailsByPhoneNumber(phone);

			}
		} catch (ValidatorException | ServiceException | DAOException e) {
			e.getMessage();
		}

		return customer;
	}

}
