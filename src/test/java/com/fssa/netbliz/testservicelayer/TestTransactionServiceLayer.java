package com.fssa.netbliz.testservicelayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.dao.TransactionDao;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.servicelayer.TransactionServiceLayer;

 class TestTransactionServiceLayer {

	Transaction trans = new Transaction();

	@Test
	 void moneyTransaction() throws Exception {

		Transaction trans = new Transaction("0987654321123456", "2345678901234568", "IDIB000K132", 10, "bill pay");

		Assertions.assertTrue(TransactionServiceLayer.moneyTransaction(trans));
	} 
	 
	@Test
	
	void printTransactions() throws Exception{
		
		String accNo = "2345678901234568";  
		
		Assertions.assertTrue(TransactionServiceLayer.printTransactions(accNo));
	}
	
	@Test
	
	void isActiveAccount() throws Exception{
		
		String accNo = "2345678901234568"; 
		
		Assertions.assertTrue(TransactionServiceLayer.isActiveAccount(accNo));
	}
	
	@Test
	
	void listOfTransaction() throws Exception{
		
		String accNo = "2345678901234568";  
		
		Assertions.assertNotNull(TransactionServiceLayer.listOfTransaction(accNo));
	}
}
