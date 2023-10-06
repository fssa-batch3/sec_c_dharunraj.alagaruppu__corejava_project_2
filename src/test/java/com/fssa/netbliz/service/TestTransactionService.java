package com.fssa.netbliz.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.exception.ServiceException;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.util.Logger;

class TestTransactionService {

	TransactionService transService = new TransactionService();

	/**
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	@Test
	void moneyTransaction() {

		Transaction trans = new Transaction("0987654321123456", "1234567890123456", "IDIB000K132", 10, "case pack");

		try {
			Assertions.assertTrue(transService.moneyTransaction(trans));
		} catch (ServiceException e) {

			e.getMessage();
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
			e.getMessage();
		}

	}

	@Test

	/**
	 * @throws Exception If there's an unexpected exception during the test.
	 */

	void listOfTransaction() {
		int id = 1;

		try {
			Logger.info(transService.listOfTransaction(id));
			Assertions.assertNotNull(transService.listOfTransaction(id));
		} catch (ServiceException e) {
			e.getMessage();
		}

	}
	
	/**
	 * Test case for checking if minimum balance penalty is applied correctly.
	 * 
	 * It checks if the minimum balance penalty is applied when the available balance is above the minimum required.
	 * The test expects the method to return true.
	 */

	@Test

	void checkMinimumBalance() { // minimum balance penalty 

		String accNo = "1234567890123456";
		double transferMoney = 19600;

		try {
			Assertions.assertTrue(transService.checkMinimumBalance(accNo, transferMoney));
		} catch (ServiceException e) {
			e.getMessage();
		}
	}

	/**
	 * Test case for checking if minimum balance penalty is applied correctly.
	 * 
	 * It checks if the minimum balance penalty is not applied when the available balance is below the minimum required.
	 * The test expects the method to return false.
	 */
	
	@Test

	void invalidCheckMinimumBalance() { // minimum balance penalty

		String accNo = "1234567890123456";
		double transferMoney = 74000;

		try { 
			Assertions.assertFalse(transService.checkMinimumBalance(accNo, transferMoney));
		} catch (ServiceException e) {
			e.getMessage();
		}

	}
}
