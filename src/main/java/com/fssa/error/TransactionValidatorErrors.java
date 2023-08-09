package com.fssa.error;

public final class TransactionValidatorErrors {

	public static final String REMARK_LENGTH_OVERLOAD = "Remark length should be below 30 charactors";

	public static final String INVALID_OBJECT_NULL = "Account can't be null";

	public static final String INVALID_AMOUNT = "Transfer amount should be greater than zero rupees";

	public static final String INVALID_REMARK = "Remark should contains below 30 characters";

	private TransactionValidatorErrors() {

	}
}
