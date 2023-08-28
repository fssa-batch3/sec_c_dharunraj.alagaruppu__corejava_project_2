package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

	void moneyTransaction() throws Exception {

		Transaction trans = new Transaction("1234567890123456", "0987654321123456", "IDIB000K132", 20, "bill pay");

		Assertions.assertTrue(TransactionService.moneyTransaction(trans));
	} 

	@Test

	/**
	 * Test case to print transactions associated with a specified account number.
	 * It asserts that the printing process of transactions is successful.
	 *
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	void printTransactions() throws Exception {
		String accNo = "1234567890123456";

		Assertions.assertTrue(TransactionService.printTransactions(accNo));
	}

	@Test

	/**
	 * Test case to retrieve a list of transactions associated with a specified
	 * account number. It asserts that the list of transactions is not null.
	 *
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	void listOfTransaction() throws Exception {
		String accNo = "1234567890123456";

		Assertions.assertNotNull(TransactionService.listOfTransaction(accNo));
	}
}
