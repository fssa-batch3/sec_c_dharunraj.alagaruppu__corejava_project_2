package com.fssa.netbliz.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.netbliz.enums.AccountEnum;
import com.fssa.netbliz.error.AccountValidatorError;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Account;

/**
 * Utility class for validating Account objects.
 */
public class AccountValidator {

	private AccountValidator() {
		// Private constructor to prevent instantiation
	}

	public static final int ACCOUNT_NUMBER_LENGTH = 16;

	public static final double ABOVE_MINIMUM_BALANCE_RANGE = 500.0;

	public static final double BELOW_MINIMUM_BALANCE_RANGE = 25000.0;

	/**
	 * Validates the provided account object's data.
	 *
	 * @param account The account object to be validated
	 * @return True if the account data is valid, false otherwise
	 * @throws ValidatorException If any validation error occurs during the process
	 */
	public static boolean validate(Account account) throws ValidatorException {

		if (account == null) {
			throw new ValidatorException(AccountValidatorError.INVALID_OBJECT_NULL);
		}

		validateAccountNumber(account.getAccountNumber());
		validateIfsc(account.getIfsc());
		validateMinimumBalance(account.getMinimumBalance());
		validatePhoneNumber(account.getPhoneNumber());
		validateType(account.getCategory());
		validateAvailableBalance(account.getAvailableBalance());
		validateMonthIntervel(account.getMonthIntervel());

		return true;
	}

	/**
	 * Validates the format and length of an account number.
	 *
	 * @param accountNumber The account number to be validated
	 * @return True if the account number is valid, false otherwise
	 * @throws ValidatorException If the account number is invalid
	 */
	public static boolean validateAccountNumber(String accountNumber) throws ValidatorException {

		if (accountNumber == null) {
			throw new ValidatorException(AccountValidatorError.INVALID_ACCOUNTNUMBER);
		} else if ("".equals(accountNumber.trim())) {
			throw new ValidatorException(AccountValidatorError.INVALID_EMPTY_ACCOUNTNUMBER);
		} else if (accountNumber.trim().length() != ACCOUNT_NUMBER_LENGTH) {
			throw new ValidatorException(AccountValidatorError.INVALID_LENGTH_ACCOUNTNUMBER);
		}

		String regexAccountNumber = "\\d{16}";
		Pattern pattern = Pattern.compile(regexAccountNumber);
		Matcher matcher = pattern.matcher(accountNumber);
		boolean isMatch = matcher.matches();
		if (!isMatch) {
			throw new ValidatorException(AccountValidatorError.INVALID_ACCOUNTNUMBER);
		}

		return true;
	}

	/**
	 * Validates an IFSC code to ensure it follows the correct format.
	 *
	 * @param ifsc The IFSC code to be validated.
	 * @return {@code true} if the IFSC code is valid, {@code false} otherwise.
	 * @throws ValidatorException If the provided IFSC code is null, empty, or
	 *                            invalid.
	 */
	public static boolean validateIfsc(String ifsc) throws ValidatorException {

		if (ifsc == null) {
			throw new ValidatorException(AccountValidatorError.INVALID_NULL_IFSCCODE);
		} else if ("".equals(ifsc.trim())) {
			throw new ValidatorException(AccountValidatorError.INVALID_EMPTY_IFSCCODE);
		}

		String regexIfscCode = "^[A-Za-z]{4}0[A-Za-z0-9]{6}$";

		Pattern pattern = Pattern.compile(regexIfscCode);
		Matcher matcher = pattern.matcher(ifsc);
		boolean isMatch = matcher.matches();
		if (!isMatch) {
			throw new ValidatorException(AccountValidatorError.INVALID_IFSCCODE);
		}

		return true;
	}

	/**
	 * Validates a phone number to ensure it follows the correct format and length.
	 *
	 * @param phoneNumber The phone number to be validated.
	 * @return {@code true} if the phone number is valid, {@code false} otherwise.
	 * @throws ValidatorException If the provided phone number is null, empty, of
	 *                            incorrect length, or invalid.
	 */
	public static boolean validatePhoneNumber(long phoneNumber) throws ValidatorException {

		String num = Long.toString(phoneNumber);

		String regexPhoneNumber = "^\\d{10}$";
		Pattern pattern = Pattern.compile(regexPhoneNumber);
		Matcher matcher = pattern.matcher(num);
		boolean isMatch = matcher.matches();

		if (!isMatch) {
			throw new ValidatorException(AccountValidatorError.INVALID_PHONENUMBER);
		}
		return true;
	}

	/**
	 * Validates an account type to ensure it is one of the valid types.
	 *
	 * @param type The account type to be validated.
	 * @return {@code true} if the account type is valid, {@code false} otherwise.
	 * @throws ValidatorException If the provided account type is null, empty, not
	 *                            recognized, or invalid.
	 */
	public static boolean validateType(AccountEnum type) throws ValidatorException {

		if (type == null) {
			throw new ValidatorException(AccountValidatorError.INVALID_NULL_TYPEOFACCOUNT);
		}

		for (AccountEnum validType : AccountEnum.values()) {
			if (validType.equals(type)) {
				return true;
			}
		}

		throw new ValidatorException(AccountValidatorError.INVALID_TYPEOFACCOUNT);
	}

	/**
	 * Validates a minimum balance to ensure it falls within an acceptable range.
	 *
	 * @param minimumBalance The minimum balance to be validated.
	 * @return {@code true} if the minimum balance is valid, {@code false}
	 *         otherwise.
	 * @throws ValidatorException If the provided minimum balance is outside the
	 *                            acceptable range.
	 */
	public static boolean validateMinimumBalance(double minimumBalance) throws ValidatorException {

		if (minimumBalance >= ABOVE_MINIMUM_BALANCE_RANGE && minimumBalance <= BELOW_MINIMUM_BALANCE_RANGE) {
			return true;
		}
		throw new ValidatorException(AccountValidatorError.INVALID_MINIMUMBALANCE);
	}

	public static boolean validateAvailableBalance(double availableBalance) throws ValidatorException {

		if (availableBalance >= Account.ZERO) {
			return true;
		}
		throw new ValidatorException(AccountValidatorError.INVALID_MINIMUMBALANCE);
	}

	public static boolean validateMonthIntervel(int monthIntervel) throws ValidatorException {

		if (monthIntervel > Account.ZERO) {
			return true;
		}
		throw new ValidatorException(AccountValidatorError.INVALID_MINIMUMBALANCE);
	}

}
