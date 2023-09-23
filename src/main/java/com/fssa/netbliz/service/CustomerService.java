package com.fssa.netbliz.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.constants.NetblizConstants;
import com.fssa.netbliz.dao.CustomerDAO;
import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Customer;
import com.fssa.netbliz.validator.AccountValidator;
import com.fssa.netbliz.validator.CustomerValidator;

public class CustomerService {

	/**
	 * Adds a customer to the system.
	 *
	 * @param customer The customer to add.
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
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}

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
			}
			else {
				throw new ServiceException("Your account is not active");
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}

		
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
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}
	}

	/**
	 * Logs in a customer using their phone number, email, and password.
	 *
	 * @param phone    The customer's phone number.
	 * @param email    The customer's email.
	 * @param password The customer's password.
	 * @return True if the login is successful, false otherwise.
	 * @throws ServiceException If there is a service-level error.
	 */
	public boolean logInCustomer(long phone, String email, String password) throws ServiceException {
		try {
			if (CustomerValidator.validateEmail(email) && CustomerValidator.validatePassword(password)
					&& AccountValidator.validatePhoneNumber(phone) && isActiveAccount(phone)
					&& isAvailableAccount(phone)) {
				return CustomerDAO.logInCustomer(phone, email, password);
			} else {

				throw new ServiceException("Data mismatch : Try again with your valid phone number and other fields");
			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		} catch (SQLException e) {
			throw new ServiceException("SQL error: " + e.getMessage());
		}
	}

	/**
	 * Retrieves customer details by phone number.
	 *
	 * @param phone The phone number to search for.
	 * @return A list of customers with the given phone number, or null if none are
	 *         found.
	 * @throws ServiceException If there is a service-level error.
	 */
	public Customer getCustomerByPhoneNumber(long phone) throws ServiceException {

		Customer customer = new Customer();
		try {
			if (AccountValidator.validatePhoneNumber(phone) && isActiveAccount(phone) && isAvailableAccount(phone)) {

				customer = CustomerDAO.getCustomerDetailsByPhoneNumber(phone);

			}
		} catch (ValidatorException e) {
			throw new ServiceException(NetblizConstants.VALIDATION_ERROR + e.getMessage());
		} catch (DAOException e) {
			throw new ServiceException(NetblizConstants.DAO_ERROR + e.getMessage());
		}

		return customer;
	}

}
