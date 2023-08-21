package com.fssa.netbliz.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.netbliz.dao.Logger;
import com.fssa.netbliz.enums.AccountEnum;
import com.fssa.netbliz.error.AccountValidatorError;
import com.fssa.netbliz.exception.AccountValidatorException;
import com.fssa.netbliz.model.Account;

public class AccountValidator {

	private AccountValidator() {

	}

	public static final int ACCOUNT_NUMBER_LENGTH = 16;
	public static final int PHONE_NUMBER_LENGTH = 10;
	public static final double ABOVE_MINIMUM_BALANCE_RANGE = 500.0;
	public static final double BELOW_MINIMUM_BALANCE_RANGE = 25000.0;

	/**
	 * Validates the provided account object's data.
	 *
	 * @param account The account object to be validated
	 * @return True if the account data is valid, false otherwise
	 * @throws AccountValidatorException If any validation error occurs during the
	 *                                   process
	 */

	public static boolean validate(Account account) throws AccountValidatorException {

		if (account == null) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_OBJECT_NULL);
		}

		validateAccountNumber(account.getAccountNumber());
		validateIfsc(account.getIfsc());
		validateMinimumBalance(account.getMinimumBalance());
		validatePhoneNumber(account.getPhoneNumber());
		validateType(account.getCategory());
		return true;
	}

	/**
	 * Validates the format and length of an account number.
	 *
	 * @param accountNumber The account number to be validated
	 * @return True if the account number is valid, false otherwise
	 * @throws AccountValidatorException If the account number is invalid
	 */

	public static boolean validateAccountNumber(String accountNumber) throws AccountValidatorException {

		if (accountNumber == null) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_ACCOUNTNUMBER);
		}

		else if ("".equals(accountNumber.trim())) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_EMPTY_ACCOUNTNUMBER);
		}

		else if (accountNumber.trim().length() != ACCOUNT_NUMBER_LENGTH) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_LENGTH_ACCOUNTNUMBER);
		}

		String regexAccountNumber = "\\d{16}";
		Pattern pattern = Pattern.compile(regexAccountNumber); // compiles the given pattern
		Matcher matcher = pattern.matcher(accountNumber); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false
		if (!isMatch) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_ACCOUNTNUMBER);
		}

		return true;
	}

	/**
	 * Validates an IFSC code to ensure it follows the correct format.
	 *
	 * @param ifsc The IFSC code to be validated.
	 * @return {@code true} if the IFSC code is valid, {@code false} otherwise.
	 * @throws AccountValidatorException If the provided IFSC code is null, empty,
	 *                                   or invalid.
	 */

	public static boolean validateIfsc(String ifsc) throws AccountValidatorException {

		if (ifsc == null) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_NULL_IFSCCODE);
		}

		else if ("".equals(ifsc.trim())) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_EMPTY_IFSCCODE);
		}

		String regexIfscCode = "^[A-Za-z]{4}0[A-Za-z0-9]{6}$";

		Pattern pattern = Pattern.compile(regexIfscCode); // compiles the given pattern
		Matcher matcher = pattern.matcher(ifsc); // matcher is going to match the IFSC code
		boolean isMatch = matcher.matches(); // give is return the boolean value true or false
		if (!isMatch) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_IFSCCODE);

		}
		return true;
	}

	/**
	 * Validates a phone number to ensure it follows the correct format and length.
	 *
	 * @param phoneNumber The phone number to be validated.
	 * @return {@code true} if the phone number is valid, {@code false} otherwise.
	 * @throws AccountValidatorException If the provided phone number is null,
	 *                                   empty, of incorrect length, or invalid.
	 */

	public static boolean validatePhoneNumber(String phoneNumber) throws AccountValidatorException {

		if (phoneNumber == null) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_NULL_PHONENUMBER);
		}

		else if ("".equals(phoneNumber)) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_EMPTY_PHONENUMBER);
		}

		else if (phoneNumber.trim().length() != PHONE_NUMBER_LENGTH) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_LENGTH_PHONENUMBER);
		}

		String regexPhoneNumber = "^[0-9]{10}$"; // This the phone number regex pattern
		Pattern pattern = Pattern.compile(regexPhoneNumber); // this regex is validate if the string is valid or not
		Matcher matcher = pattern.matcher(phoneNumber); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false

		if (!isMatch) {
			Logger.info("Valid mobilenumber");
			throw new AccountValidatorException(AccountValidatorError.INVALID_PHONENUMBER);
		}
		return true;

	}

	/**
	 * Validates an account type to ensure it is one of the valid types.
	 *
	 * @param type The account type to be validated.
	 * @return {@code true} if the account type is valid, {@code false} otherwise.
	 * @throws AccountValidatorException If the provided account type is null,
	 *                                   empty, not recognized, or invalid.
	 */

	public static boolean validateType(String type) throws AccountValidatorException {

		if (type == null) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_NULL_TYPEOFACCOUNT);
		} else if ("".equals(type)) {

			throw new AccountValidatorException(AccountValidatorError.INVALID_EMPTY_TYPEOFACCOUNT);
		}

		for (AccountEnum validType : AccountEnum.values()) { // number of elements present
																// in the object
			if (validType.toString().equalsIgnoreCase(type)) {

				return true;
			}

		}

		throw new AccountValidatorException(AccountValidatorError.INVALID_TYPEOFACCOUNT);

	}

	/**
	 * Validates a minimum balance to ensure it falls within an acceptable range.
	 *
	 * @param minimumBalance The minimum balance to be validated.
	 * @return {@code true} if the minimum balance is valid, {@code false}
	 *         otherwise.
	 * @throws AccountValidatorException If the provided minimum balance is outside
	 *                                   the acceptable range.
	 */

	public static boolean validateMinimumBalance(double minimumBalance) throws AccountValidatorException {

		if (minimumBalance >= ABOVE_MINIMUM_BALANCE_RANGE && minimumBalance <= BELOW_MINIMUM_BALANCE_RANGE) {
			return true;
		}
		throw new AccountValidatorException(AccountValidatorError.INVALID_MINIMUMBALANCE);
	}

}
