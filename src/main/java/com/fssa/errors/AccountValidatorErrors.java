package com.fssa.errors;

public interface AccountValidatorErrors {

	// object is not created properly
	public static final String INVALID_OBJECT_CREATION = "Account object is not created";

	// object is null
	public static final String INVALID_OBJECT_NULL = "Account can't be null";

	// ID is less than zero
	public static final String INVALID_ID = "Id can't be less than zero";

	// account number is null or empty
	public static final String INVALID_ACCOUNTNUMBER = "Account number should have 16 digit numbers";

	// account number is null
	public static final String INVALID_NULL_ACCOUNTNUMBER = "Account number can't be null";

	// account number is empty
	public static final String INVALID_EMPTY_ACCOUNTNUMBER = "Account number can't be empty";

	// account number doesn't have 16 digits
	public static final String INVALID_LENGTH_ACCOUNTNUMBER = "Account number should have 16 numbers";

	// IFSC code is null or empty
	public static final String INVALID_IFSCCODE = "IFSC code should have 11 numeric characters";

	// IFSC code is null
	public static final String INVALID_NULL_IFSCCODE = "IFSC code can't be null";

	// IFSC code is empty
	public static final String INVALID_EMPTY_IFSCCODE = "IFSC code can't be empty";

	// phone number is null or empty
	public static final String INVALID_PHONENUMBER = "Phone number should contain exactly 10 digit numbers";

	// phone number is null
	public static final String INVALID_NULL_PHONENUMBER = "Phone number can't be null";

	// phone number is empty
	public static final String INVALID_EMPTY_PHONENUMBER = "Phone number can't be empty";

	// phone number doesn't have 10 digits
	public static final String INVALID_LENGTH_PHONENUMBER = "Phone number should have 10 numbers";

	// account type doesn't exist
	public static final String INVALID_TYPEOFACCOUNT = "Exits account type is mismatched";

	// account type is null
	public static final String INVALID_NULL_TYPEOFACCOUNT = "Account type can't be null";

	// account type is empty
	public static final String INVALID_EMPTY_TYPEOFACCOUNT = "Account type can't be empty";

	// minimum balance is not within the allowed range
	public static final String INVALID_MINIMUMBALANCE = "Minimum balance should be above 500 rupee and below 25000 rupee";

	// a service layer issue where an object is null
	public static final String SERVICE_LAYER_ERROR = "Object can't be null";

	// an invalid input in the service layer
	public static final String INVALID_SERVICE_LAYER_ERROR = "Invalid input";

	// add a null account to the database
	public static final String NULL_ADD_ACCOUNT = "Account can't be null when adding to the database";
}
