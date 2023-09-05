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
	        }
	    } catch (ValidatorException e) {
	        throw new ServiceException("Validation error: " + e.getMessage());
	    } catch (DAOException e) {
	        throw new ServiceException("DAO error: " + e.getMessage());
	    }

	    return false;
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
	    } catch (ValidatorException e) {
	        throw new ServiceException("Validation error: " + e.getMessage());
	    } catch (DAOException e) {
	        throw new ServiceException("DAO error: " + e.getMessage());
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
	        }
	    } catch (ValidatorException e) {
	        throw new ServiceException("Validation error: " + e.getMessage());
	    } catch (DAOException e) {
	        throw new ServiceException("DAO error: " + e.getMessage());
	    }

	    return false;
	}

	/**
	 * Logs in a customer using their phone number, email, and password.
	 *
	 * @param phone The customer's phone number.
	 * @param email The customer's email.
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
	        }
	    } catch (ValidatorException e) {
	        throw new ServiceException("Validation error: " + e.getMessage());
	    } catch (DAOException e) {
	        throw new ServiceException("DAO error: " + e.getMessage());
	    } catch (SQLException e) {
	        throw new ServiceException("SQL error: " + e.getMessage());
	    }

	    return false;
	}

	/**
	 * Retrieves customer details by phone number.
	 *
	 * @param phone The phone number to search for.
	 * @return A list of customers with the given phone number, or null if none are found.
	 * @throws ServiceException If there is a service-level error.
	 */
	public List<Customer> getCustomerByPhoneNumber(long phone) throws ServiceException {
	    try {
	        if (AccountValidator.validatePhoneNumber(phone) && isActiveAccount(phone) && isAvailableAccount(phone)) {
	            return CustomerDAO.getCustomerDetailsByPhoneNumber(phone);
	        }
	    } catch (ValidatorException e) {
	        throw new ServiceException("Validation error: " + e.getMessage());
	    } catch (DAOException e) {
	        throw new ServiceException("DAO error: " + e.getMessage());
	    }

	    return null;
	}

}
