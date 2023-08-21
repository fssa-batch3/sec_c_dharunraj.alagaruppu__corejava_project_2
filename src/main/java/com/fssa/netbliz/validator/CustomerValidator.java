package com.fssa.netbliz.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fssa.netbliz.error.CustomerValidatorError;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.CustomerValidatorException;
import com.fssa.netbliz.model.Customer;

public class CustomerValidator {

	private CustomerValidator() {

	}

	public static final int LENGTH_OF_FIRST_NAME = 30;

	public static final int LENGTH_OF_LAST_NAME = 30; 

	/**
	 * Validates the provided customer object's data.
	 *
	 * @param customer The customer object to be validated
	 * @return True if the customer data is valid, false otherwise
	 * @throws CustomerValidatorException If any validation error occurs during the
	 *                                    process
	 * @throws AccountValidatorException  If an account validation error occurs
	 *                                    during the process
	 */

	public static boolean validate(Customer customer) throws CustomerValidatorException, AccountValidatorException {

		if (customer == null) {

			throw new CustomerValidatorException(CustomerValidatorError.NULL_OBJECT);
		}

		validateFirstName(customer.getfName());
		validateLastName(customer.getlName());
		validateEmail(customer.getEmail());
		validateComparePassword(customer.getPassword(), customer.getconfirmPassword());
		AccountValidator.validatePhoneNumber(customer.getPhoneNumber());

		return true;
 
	}

	/**
	 * Validates the first name of a customer.
	 *
	 * @param fName The first name to be validated
	 * @return True if the first name is valid, false otherwise
	 * @throws CustomerValidatorException If the first name is invalid
	 */

	public static boolean validateFirstName(String fName) throws CustomerValidatorException {

		if (fName == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NULL_FIRST_NAME);
		}

		else if ("".equals(fName.trim())) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_EMPTY_FIRST_NAME);
		}

		else if (fName.trim().length() >= LENGTH_OF_FIRST_NAME) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_LENGTH_FIRST_NAME);
		}

		String regexFirstName = "^[A-Za-z\\s]+$";
		Pattern pattern = Pattern.compile(regexFirstName); // compiles the given pattern
		Matcher matcher = pattern.matcher(fName); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (isMatch != true) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NAME);
		}
		return true;

	}

	/**
	 * Validates the last name of a customer.
	 *
	 * @param lName The last name to be validated
	 * @return True if the last name is valid, false otherwise
	 * @throws CustomerValidatorException If the last name is invalid
	 */

	public static boolean validateLastName(String lName) throws CustomerValidatorException {

		if (lName == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NULL_LAST_NAME);
		}

		else if ("".equals(lName.trim())) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_EMPTY_LAST_NAME);
		}

		else if (lName.trim().length() >= LENGTH_OF_LAST_NAME) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_LENGTH_LAST_NAME);
		}

		String regexFirstName = "^[A-Za-z\\s]+$";
		Pattern pattern = Pattern.compile(regexFirstName); // compiles the given pattern
		Matcher matcher = pattern.matcher(lName); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (isMatch != true) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NAME);
		}
		return true;

	}

	/**
	 * Validates the email address of a customer.
	 *
	 * @param email The email address to be validated
	 * @return True if the email address is valid, false otherwise
	 * @throws CustomerValidatorException If the email address is invalid
	 */

	public static boolean validateEmail(String email) throws CustomerValidatorException {

		if (email == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NULL_EMAIL);
		}

		else if ("".equals(email.trim())) {
			throw new CustomerValidatorException(CustomerValidatorError.INVALID_EMPTY_EMAIL);
		}

		String regexEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regexEmail); // compiles the given pattern
		Matcher matcher = pattern.matcher(email); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (isMatch != true) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_PATTERN_EMAIL);
		}
		return true;

	}

	/**
	 * Validates a customer's password against a strong password pattern.
	 *
	 * @param password The password to be validated
	 * @return True if the password is strong, false otherwise
	 * @throws CustomerValidatorException If the password is invalid
	 */

	public static boolean validatePassword(String password) throws CustomerValidatorException {

		if (password == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NULL_PASSWORD);
		}

		else if ("".equals(password.trim())) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_EMPTY_PASSWORD);
		}

		String regexStrongPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		Pattern pattern = Pattern.compile(regexStrongPassword); // compiles the given pattern
		Matcher matcher = pattern.matcher(password); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (isMatch != true) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_PATTERN_PASSWORD);
		}
		return true;

	}

	/**
	 * Validates if the provided password and confirmation password match and are
	 * strong.
	 *
	 * @param pass    The password to be validated
	 * @param confirm The confirmation password to be validated
	 * @return True if both passwords match and are strong, false otherwise
	 * @throws CustomerValidatorException If the passwords do not match or are
	 *                                    invalid
	 */

	public static boolean validateComparePassword(String pass, String confirm) throws CustomerValidatorException {

		if (pass == null || confirm == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NULL_PASSWORD);
		}

		else if ("".equals(pass.trim()) || "".equals(confirm.trim())) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_EMPTY_PASSWORD);
		}

		boolean password = validatePassword(pass);
		boolean confirmPassword = validatePassword(confirm);

		if (password != true || confirmPassword != true) {

			throw new CustomerValidatorException(CustomerValidatorError.WRONG_PASSWORD); 
		}

		return true;

	}

}
