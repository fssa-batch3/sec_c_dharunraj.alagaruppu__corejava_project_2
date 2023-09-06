package com.fssa.netbliz.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.model.Transaction;

class TestTransactionService {

	TransactionService transService = new TransactionService();

	/**
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	@Test
	void moneyTransaction() {

		Transaction trans = new Transaction("1234567890123456", "0987654321123456", "IDIB000K132", 10, "case pack");

		try {
			Assertions.assertTrue(transService.moneyTransaction(trans));
		} catch (ServiceException e) {

			Assertions.fail(e.getMessage());
		}
	}

	/**
	 * @throws ServiceException when the moneyTransaction method encounters an error
	 */

	@Test

	void invalidMoneyTransaction() {

		Transaction trans = new Transaction("1234765890123456", "0987654321123456", "IDIB000K132", 10, "case pack");

		assertThrows(ServiceException.class, () -> transService.moneyTransaction(trans));
	}

	@Test

	/**
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	void printTransactions() {
		int id = 1;

		try {
			Assertions.assertTrue(transService.printTransactions(id));
		} catch (ServiceException e) {
			Assertions.fail(e.getMessage());
		}

	}

	@Test

	/**
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	void listOfTransaction() {
		int id = 1;

		try {
			Assertions.assertNotNull(transService.listOfTransaction(id));
		} catch (ServiceException e) {
			Assertions.fail(e.getMessage());
		}

	}
}
