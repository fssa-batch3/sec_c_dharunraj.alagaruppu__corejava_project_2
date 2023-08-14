package com.fssa.netbliz.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.netbliz.error.AccountValidatorError;
import com.fssa.netbliz.error.CustomerValidatorError;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.exception.CustomerValidatorException;
import com.fssa.netbliz.model.Customer;

public class CustomerValidator {

	private CustomerValidator() {

	}

	public static final int LENGTH_OF_FIRST_NAME = 30;

	public static final int LENGTH_OF_LAST_NAME = 30;

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

	public static boolean validateFirstName(String fName) throws CustomerValidatorException {

		if (fName == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NAME);
		}

		else if ("".equals(fName.trim())) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NAME);
		}

		else if (fName.trim().length() >= LENGTH_OF_FIRST_NAME) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NAME);
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

	public static boolean validateLastName(String lName) throws CustomerValidatorException {

		if (lName == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NAME);
		}

		else if ("".equals(lName.trim())) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NAME);
		}

		else if (lName.trim().length() >= LENGTH_OF_LAST_NAME) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_NAME);
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

	public static boolean validateEmail(String email) throws CustomerValidatorException {

		if (email == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_EMAIL);
		}

		else if ("".equals(email.trim())) {
			throw new CustomerValidatorException(CustomerValidatorError.INVALID_EMAIL);
		}

		else if (email.trim().length() >= LENGTH_OF_LAST_NAME) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_EMAIL);
		}

		String regexEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regexEmail); // compiles the given pattern
		Matcher matcher = pattern.matcher(email); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (isMatch != true) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_EMAIL);
		}
		return true;

	}

	public static boolean validatePassword(String password) throws CustomerValidatorException {

		if (password == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_PASSWORD);
		}

		else if ("".equals(password.trim())) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_PASSWORD);
		}

		else if (password.trim().length() >= LENGTH_OF_LAST_NAME) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_PASSWORD);
		}

		String regexStrongPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		Pattern pattern = Pattern.compile(regexStrongPassword); // compiles the given pattern
		Matcher matcher = pattern.matcher(password); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (isMatch != true) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_PASSWORD);
		}
		return true;

	}

	public static boolean validateComparePassword(String pass, String confirm) throws CustomerValidatorException {

		if (pass == null || confirm == null) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_PASSWORD);
		}

		else if ("".equals(pass.trim()) || "".equals(confirm.trim())) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_PASSWORD);
		}

		boolean password = validatePassword(pass);
		boolean confirmPassword = validatePassword(confirm);

		if (password != true || confirmPassword != true) {

			throw new CustomerValidatorException(CustomerValidatorError.INVALID_PASSWORD);
		}

		return true;

	}

}
