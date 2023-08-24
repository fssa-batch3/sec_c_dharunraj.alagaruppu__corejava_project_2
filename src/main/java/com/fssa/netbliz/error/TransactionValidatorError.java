package com.fssa.netbliz.error;

public final class TransactionValidatorError {

	// public static final String REMARK_LENGTH_OVERLOAD = "Remark length should be below 30 charactors";

	public static final String INVALID_OBJECT_NULL = " Transfer account can't be null";

	public static final String INVALID_AMOUNT = "Transfer amount should be greater than zero rupees";

	public static final String INVALID_REMARK = "Remark should contains below 50 characters";

	private TransactionValidatorError() {

	}
}
