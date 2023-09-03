package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.model.Transaction;

class TestTransactionService {

	TransactionService transService = new TransactionService();

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
			Assertions.assertTrue(transService.moneyTransaction(trans));
		} catch (ServiceException e) {
			Assertions.fail(e);
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
		int id = 1;

			try {
				Assertions.assertTrue(transService.printTransactions(id));
			} catch (ServiceException e) {
				Assertions.fail(e);
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
		int id = 1;

		
			try {
				Assertions.assertNotNull(transService.listOfTransaction(id));
			} catch (ServiceException e) {
				Assertions.fail(e);
			}
		
	}
}
