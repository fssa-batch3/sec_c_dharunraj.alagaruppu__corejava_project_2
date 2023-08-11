package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.netbliz.error.TransactionDaoError;
import com.fssa.netbliz.exception.TransactionDaoException;
import com.fssa.netbliz.model.Transaction;
import com.fssa.netbliz.util.ConnectionUtil;

public class TransactionDao {

	private TransactionDao() {
		// Private constructor to prevent instantiation
	}

	public static final int INITIALIZE_ZERO = 0;
	public static final String CRIDIT_DENOTES = "credited";
	public static final String DEBIT_DENOTES = "debited";

	
	static double holderBalance = INITIALIZE_ZERO;
	static double remittanceBalance = INITIALIZE_ZERO;

	// Checks if an account is active
	public static boolean isActiveAccount(String holder) throws TransactionDaoException {
		String query = "SELECT acc_no, avl_balance FROM account WHERE acc_no = ? AND is_active = true";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, holder);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			throw new TransactionDaoException(TransactionDaoError.INVALID_ACCOUNT_NUMBER);
		}
		return false;
	}

	// Retrieves the available balance of the account holder
	public static double accountHolderConditions(Transaction trans, Connection con) throws TransactionDaoException {
		// Query to check account conditions
		String query = "SELECT acc_no, avl_balance FROM account WHERE acc_no = ? AND is_active = true AND avl_balance >= ?";

		double avlBalance = INITIALIZE_ZERO;

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, trans.getAccountHolderAccNo());
			pst.setDouble(2, trans.getTransferAmount());
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					avlBalance = rs.getDouble("avl_balance") - trans.getTransferAmount();
				}
			}
		} catch (SQLException e) {
			throw new TransactionDaoException(TransactionDaoError.INVALID_ACCOUNT_NUMBER);
		}

		return avlBalance;
	}

	// Retrieves the available balance of the remittance account
	public static double remittanceAccountConditions(Transaction trans, Connection con) throws TransactionDaoException {
		// Query to check remittance account conditions
		String query = "SELECT acc_no, avl_balance FROM account WHERE acc_no = ? AND ifsc = ? AND is_active = true";

		double avlBalance = INITIALIZE_ZERO;

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, trans.getRemittanceAccNo());
			pst.setString(2, trans.getReceiverIfscCode());
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					avlBalance = rs.getDouble("avl_balance") + trans.getTransferAmount();
				}
			}
		} catch (SQLException e) {
			throw new TransactionDaoException(TransactionDaoError.INVALID_ACCOUNT_NUMBER);
		}

		return avlBalance;
	}

	// Updates the account holder's account
	public static boolean updateHolderAccount(Transaction trans) throws TransactionDaoException {
		String query = "UPDATE account SET avl_balance = ? WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				holderBalance = accountHolderConditions(trans, con);
				remittanceBalance = remittanceAccountConditions(trans, con);
				pst.setDouble(1, holderBalance);
				pst.setString(2, trans.getAccountHolderAccNo());
				pst.executeUpdate();
				updateRemittanceAccount(trans, con);
			}
		} catch (SQLException e) {
			throw new TransactionDaoException(TransactionDaoError.INVALID_ACCOUNT_NUMBER);
		}

		return true;
	}

	// Updates the remittance account
	public static boolean updateRemittanceAccount(Transaction trans, Connection con) throws TransactionDaoException {
		String query = "UPDATE account SET avl_balance = ? WHERE acc_no = ?";

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setDouble(1, remittanceBalance);
			pst.setString(2, trans.getRemittanceAccNo());
			pst.executeUpdate();
			insertAccountHolderDetails(trans, con);
		} catch (SQLException e) {
			throw new TransactionDaoException(TransactionDaoError.INVALID_ACCOUNT_NUMBER);
		}
		return true;
	}

	// Inserts details of the account holder's transaction
	public static boolean insertAccountHolderDetails(Transaction trans, Connection con) throws TransactionDaoException {
		String query = "INSERT INTO transaction (acc_holder, remittance, trans_status, trans_amount, avl_balance, remark) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, trans.getAccountHolderAccNo());
			pst.setString(2, trans.getRemittanceAccNo());
			pst.setString(3, CRIDIT_DENOTES);
			pst.setDouble(4, trans.getTransferAmount());
			pst.setDouble(5, holderBalance);
			pst.setString(6, trans.getRemark());
			pst.executeUpdate();
			insertRemittanceAccountDetails(trans, con);
		} catch (SQLException e) {
			throw new TransactionDaoException(TransactionDaoError.INVALID_ACCOUNT_NUMBER);
		}

		return true;
	}

	// Inserts details of the remittance account's transaction
	public static boolean insertRemittanceAccountDetails(Transaction trans, Connection con)
			throws TransactionDaoException {
		String query = "INSERT INTO transaction (acc_holder, remittance, trans_status, trans_amount, avl_balance, remark) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pst = con.prepareStatement(query)) {
			pst.setString(1, trans.getRemittanceAccNo());
			pst.setString(2, trans.getAccountHolderAccNo());
			pst.setString(3, DEBIT_DENOTES);
			pst.setDouble(4, trans.getTransferAmount());
			pst.setDouble(5, remittanceBalance);
			pst.setString(6, trans.getRemark());
			pst.executeUpdate();
			Logger.info("Check DataBase"); // It's assumed that Logger is a valid logging mechanism
		} catch (SQLException e) {
			throw new TransactionDaoException(TransactionDaoError.INVALID_ACCOUNT_NUMBER);
		}

		return true;
	}

	// Lists transactions for a given account number
	public static List<Object> listTransaction(String accNo) throws TransactionDaoException {
		ArrayList<Object> list = new ArrayList<>();

		String query = "SELECT * FROM transaction WHERE acc_holder = ? OR remittance = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, accNo);
				pst.setString(2, accNo);

				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						Transaction trans = new Transaction();
						trans.setAccountHolderAccNo(rs.getString("acc_holder"));
						trans.setRemittanceAccNo(rs.getString("remittance"));
						trans.setTransferAmount(rs.getDouble("trans_amount"));
						trans.setRemark(rs.getString("remark"));
						list.add(trans);
					}
				}
			}
		} catch (SQLException e) {
			throw new TransactionDaoException(TransactionDaoError.NON_TRANSACTION);
		}

		return list;
	}

	// Prints transactions for a given account number
	public static boolean printTransactions(String accNo) throws TransactionDaoException {
		List<Object> transList = listTransaction(accNo);

		if (transList.isEmpty()) {
			throw new TransactionDaoException(TransactionDaoError.NON_TRANSACTION);
		}

		for (Object list : transList) {
			Logger.info(list); // It's assumed that Logger is a valid logging mechanism
		}

		return true;
	}
}
