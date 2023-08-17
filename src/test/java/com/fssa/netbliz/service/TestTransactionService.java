package com.fssa.netbliz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.netbliz.model.Transaction;

class TestTransactionService {

	@Test
	void moneyTransaction() throws Exception {
		// Create a test Transaction object
		Transaction trans = new Transaction("1234567890123456", "2345678901234567", "IDIB000K132", 10, "treatment");
 
		// Perform the money transaction and assert true if successful
		Assertions.assertTrue(TransactionService.moneyTransaction(trans));
	}
 
	@Test
	void printTransactions() throws Exception {
		String accNo = "1234567890123456"; 

		// Print transactions for the given account number and assert true
		Assertions.assertTrue(TransactionService.printTransactions(accNo));
	} 

	@Test 
	void isActiveAccount() throws Exception {
		String accNo = "1234567890123454";

		// Check if the account is active and assert true if active
		Assertions.assertTrue(TransactionService.isActiveAccount(accNo));
	}

	@Test
	void listOfTransaction() throws Exception {
		String accNo = "1234567890123455";

		// A list of transactions and assert that the list is not null
		Assertions.assertNotNull(TransactionService.listOfTransaction(accNo));
	}
}
