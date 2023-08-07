package com.fssa.netbliz.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.errors.AccountValidatorErrors;
import com.fssa.netbliz.enums.AccountEnum;
import com.fssa.netbliz.exception.AccountValidatorExceptions;
import com.fssa.netbliz.model.Account;

public class AccountValidator {
 
	public static boolean validate(Account account) throws AccountValidatorExceptions { 

		if (account == null) {  

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_OBJECT_NULL); 
		}
		validateAccountNumber(account.getAccountNumber());
		validateIfsc(account.getIfsc());
		validateMinimumBalance(account.getMinimumBalance());
		validatePhoneNumber(account.getPhoneNumber());
		validateType(account.getCategory());
		return true;  
	}  

	// validateAccountNumber validate method is check the string is null or empty or
	// less than the 16 digit number..

	public static boolean validateAccountNumber(String accountNumber) throws AccountValidatorExceptions {

		if (accountNumber == null) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_ACCOUNTNUMBER);
		}

		else if ("".equals(accountNumber.trim())) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_EMPTY_ACCOUNTNUMBER);
		}

		else if (accountNumber.trim().length() != 16) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_LENGTH_ACCOUNTNUMBER);
		}

		String regexAccountNumber = "\\d{16}";
		Pattern pattern = Pattern.compile(regexAccountNumber); // compiles the given pattern
		Matcher matcher = pattern.matcher(accountNumber); // matcher matches the given string with compiled pattern
		Boolean isMatch = matcher.matches(); // give final output as true or false
		if (!isMatch) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_ACCOUNTNUMBER);
		}

		return true;
	}

	// validateIfsc validate method is going to check the IFSC code is valid or
	// not..

	public static boolean validateIfsc(String ifsc) throws AccountValidatorExceptions {

		if (ifsc == null) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_NULL_IFSCCODE);
		}

		else if ("".equals(ifsc.trim())) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_EMPTY_IFSCCODE);
		}

		String regexIfscCode = "^[A-Za-z]{4}0[A-Za-z0-9]{6}$";

		Pattern pattern = Pattern.compile(regexIfscCode); // compiles the given pattern
		Matcher matcher = pattern.matcher(ifsc); // matcher is going to match the IFSC code
		boolean isMatch = matcher.matches(); // give is return the boolean value true or falase
		if (!isMatch) {
			System.out.println("Invalid ifsc");

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_IFSCCODE);

		} 
		return true;
	}

	// validatePhoneNumber method is going to validate the phone number length and
	// null or not..

	public static boolean validatePhoneNumber(String phoneNumber) throws AccountValidatorExceptions {

		if (phoneNumber == null) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_NULL_PHONENUMBER);
		}

		else if ("".equals(phoneNumber)) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_EMPTY_PHONENUMBER);
		}

		else if (phoneNumber.trim().length() != 10) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_LENGTH_PHONENUMBER);
		}

		String regexPhoneNumber = "^[0-9]{10}$"; // This the phone number regex pattern
		Pattern pattern = Pattern.compile(regexPhoneNumber); // this regex is validate if the string is valid or not
		Matcher matcher = pattern.matcher(phoneNumber); // matcher matches the given string with compiled pattern
		boolean isMatch = matcher.matches(); // give final output as true or false

		if (!isMatch) {
			System.out.println("Valid mobilenumber");
			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_PHONENUMBER);
		}
		return true;

	}

	// isValidType method is validate given type of the bank is given by user..

	public static boolean validateType(String type) throws AccountValidatorExceptions {

		if (type == null) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_NULL_TYPEOFACCOUNT);
		} else if ("".equals(type)) {

			throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_EMPTY_TYPEOFACCOUNT);
		}

		for (AccountEnum validType : AccountEnum.values()) { // number of elements present
																// in the object
			if (validType.toString().equalsIgnoreCase(type)) {
				// System.out.println("Valid account type from account validater");
				return true;
			}

		}

		throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_TYPEOFACCOUNT);

	}

	// validateMinimumBalance method is validate if minimum balance is required..

	public static boolean validateMinimumBalance(double minimumBalance) throws AccountValidatorExceptions {

		if (minimumBalance >= 500.0 && minimumBalance <= 25000.0) {
			// System.out.println("Valid minimum balance");
			return true;
		}
		throw new AccountValidatorExceptions(AccountValidatorErrors.INVALID_MINIMUMBALANCE);
	}

}
