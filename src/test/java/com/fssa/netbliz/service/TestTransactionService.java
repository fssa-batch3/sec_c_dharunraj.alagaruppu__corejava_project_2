package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.exception.DAOException;
import com.fssa.netbliz.exception.ValidatorException;
import com.fssa.netbliz.model.Transaction;

class TestTransactionService {

	@Test

	/**
	 * Test case to perform a money transaction using the TransactionService.
	 * Creates a Transaction object with specified details and asserts that the
	 * money transaction process is successful.
	 *
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	void moneyTransaction() {

		Transaction trans = new Transaction("1234567890123456", "0987654321123456", "IDIB000K132", 10, "case pack");

		try {  
			Assertions.assertTrue(TransactionService.moneyTransaction(trans));
		} catch (ValidatorException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test

	/**
	 * Test case to print transactions associated with a specified account number.
	 * It asserts that the printing process of transactions is successful.
	 *
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	void printTransactions() {
		String accNo = "1234567890123456";

		try {
			Assertions.assertTrue(TransactionService.printTransactions(accNo));
		} catch (ValidatorException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test

	/**
	 * Test case to retrieve a list of transactions associated with a specified
	 * account number. It asserts that the list of transactions is not null.
	 *
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	void listOfTransaction() {
		String accNo = "1234567890123456";

		try {
			Assertions.assertNotNull(TransactionService.listOfTransaction(accNo));
		} catch (ValidatorException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
