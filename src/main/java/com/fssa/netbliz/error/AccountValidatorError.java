package com.fssa.netbliz.error;

public final class AccountValidatorError {

	//  account object was not created properly.
	public static final String INVALID_OBJECT_CREATION = "Account object is not created";

	//  account object is null, which is not allowed.
	public static final String INVALID_OBJECT_NULL = "Account can't be null";

	//  provided ID is less than zero, which is not valid.
	public static final String INVALID_ID = "Id can't be less than zero";

	//  the account number should consist of 16 digit numbers.
	public static final String INVALID_ACCOUNTNUMBER = "Account number should have 16 digit numbers";

	//  the account number is null, which is not allowed.
	public static final String INVALID_NULL_ACCOUNTNUMBER = "Account number can't be null";

	//  the account number is empty, which is not allowed.
	public static final String INVALID_EMPTY_ACCOUNTNUMBER = "Account number can't be empty";

	//  the account number should have exactly 16 digits.
	public static final String INVALID_LENGTH_ACCOUNTNUMBER = "Account number should have 16 numbers";

	//  the IFSC code should consist of 11 numeric characters.
	public static final String INVALID_IFSCCODE = "IFSC code should have 11 numeric characters";

	//  the IFSC code is null, which is not allowed.
	public static final String INVALID_NULL_IFSCCODE = "IFSC code can't be null";

	//  the IFSC code is empty, which is not allowed.
	public static final String INVALID_EMPTY_IFSCCODE = "IFSC code can't be empty";

	//  the phone number should consist of exactly 10 digit numbers.
	public static final String INVALID_PHONENUMBER = "Phone number should contain exactly 10 digit numbers";

	//  the phone number is null, which is not allowed.
	public static final String INVALID_NULL_PHONENUMBER = "Phone number can't be null";

	//  the phone number is empty, which is not allowed.
	public static final String INVALID_EMPTY_PHONENUMBER = "Phone number can't be empty";

	//  the phone number should have exactly 10 digits.
	public static final String INVALID_LENGTH_PHONENUMBER = "Phone number should have 10 numbers";

	//  the specified account type does not exist or is mismatched.
	public static final String INVALID_TYPEOFACCOUNT = "Exits account type is mismatched";

	//  the account type is null, which is not allowed.
	public static final String INVALID_NULL_TYPEOFACCOUNT = "Account type can't be null";

	//  the account type is empty, which is not allowed.
	public static final String INVALID_EMPTY_TYPEOFACCOUNT = "Account type can't be empty";

	//  the minimum balance provided is not within the allowed range.
	public static final String INVALID_MINIMUMBALANCE = "Minimum balance should be above 500 rupee and below 25000 rupee";

	// Indicates a service layer issue where an object is null.
	public static final String SERVICE_LAYER_ERROR = "Object can't be null";

	// Indicates an invalid input in the service layer.
	public static final String INVALID_SERVICE_LAYER_ERROR = "Invalid input";

	// Indicates an attempt to add a null account to the database, which is not allowed.
	public static final String NULL_ADD_ACCOUNT = "Account can't be null when adding to the database";

	
	private AccountValidatorError() {
		
		
	}
}
