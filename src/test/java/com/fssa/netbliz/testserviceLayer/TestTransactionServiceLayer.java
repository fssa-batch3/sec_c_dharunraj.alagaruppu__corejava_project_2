package com.fssa.netbliz.testserviceLayer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.netbliz.dao.TransactionDao;
import com.fssa.netbliz.model.Transaction;

public class TestTransactionServiceLayer {

	Transaction trans = new Transaction();

	@Test
	public void moneyTransaction() throws Exception {

		Transaction trans = new Transaction("1234567890123456", "0987654321123456", "IDIB000K132", 10, "bill pay");

		Assertions.assertTrue(TransactionDao.updateHolderAccount(trans));
	}
}
