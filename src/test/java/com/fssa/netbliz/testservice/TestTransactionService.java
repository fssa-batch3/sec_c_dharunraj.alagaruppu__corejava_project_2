package com.fssa.netbliz.testservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.service.TransactionService;

class TestTransactionService {

	@Test
	void moneyTransaction() throws Exception {
		// Create a test Transaction object
		Transaction trans = new Transaction("1927251381123456", "1314527190123459", "IDIB000K132", 10, "bill pay");
 
		// Perform the money transaction and assert true if successful
		Assertions.assertTrue(TransactionService.moneyTransaction(trans));
	}

	@Test
	void printTransactions() throws Exception {
		String accNo = "2345678901234568"; 

		// Print transactions for the given account number and assert true
		Assertions.assertTrue(TransactionService.printTransactions(accNo));
	}

	@Test 
	void isActiveAccount() throws Exception {
		String accNo = "2345678901234568";

		// Check if the account is active and assert true if active
		Assertions.assertTrue(TransactionService.isActiveAccount(accNo));
	}

	@Test
	void listOfTransaction() throws Exception {
		String accNo = "2345678901234568";

		// A list of transactions and assert that the list is not null
		Assertions.assertNotNull(TransactionService.listOfTransaction(accNo));
	}
}
