package com.fssa.netbliz.error;

public class CustomerValidatorError {

	// object cannot be empty, i.e., it must have some content or value.
	public static final String NULL_OBJECT = "Object can't be empty";

	//  name should only contain characters, not special characters or numbers.
	public static final String INVALID_NAME = "Name should contain only characters";

	// password provided is not strong
	public static final String INVALID_PASSWORD = "Your password is not strong ";

	// first name cannot be null.
	public static final String INVALID_NULL_FIRST_NAME = "First name can't be null";

	// first name cannot be empty.
	public static final String INVALID_EMPTY_FIRST_NAME = "First name can't be empty";

	// first name should have fewer than 30 characters.
	public static final String INVALID_LENGTH_FIRST_NAME = "First name should have under 30 characters";

	// last name cannot be null.
	public static final String INVALID_NULL_LAST_NAME = "Last name can't be null";

	// last name cannot be empty.
	public static final String INVALID_EMPTY_LAST_NAME = "Last name can't be empty";

	// last name should have fewer than 30 characters.
	public static final String INVALID_LENGTH_LAST_NAME = "Last name should have under 30 characters";

	// email id cannot be null.
	public static final String INVALID_NULL_EMAIL = "Email id can't be null";

	// email id cannot be empty.
	public static final String INVALID_EMPTY_EMAIL = "Email id can't be empty";

	// email should follow a pattern containing '@' and '.' to be valid.
	public static final String INVALID_PATTERN_EMAIL = "Email should contain '@' and '.'";

	// password cannot be null.
	public static final String INVALID_NULL_PASSWORD = "Password can't be null";

	// password cannot be empty.
	public static final String INVALID_EMPTY_PASSWORD = "Password can't be empty";

	// password should follow a pattern including at least 1 number and 1 special character.
	public static final String INVALID_PATTERN_PASSWORD = "Password should have 1 number and 1 special character length of the password above 8 ";

	// password and confirm password provided do not match.
	public static final String WRONG_PASSWORD = "Both password and confirm password is mismatched";


}
