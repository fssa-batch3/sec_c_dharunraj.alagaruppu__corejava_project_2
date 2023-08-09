package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.error.TransactionDaoErrors;
import com.fssa.netbliz.exception.TransactionDaoException;
import com.fssa.netbliz.model.Transaction;

public class TransactionDao {

	public static final int INITIALIZE_ZERO = 0;

	static double holderBalance = INITIALIZE_ZERO;
	static double remittanceBalance = INITIALIZE_ZERO;

	public static boolean isActiveAccount(String holder) throws TransactionDaoException {

		String query = "SELECT acc_no,avl_balance FROM account WHERE acc_no = ? AND is_active = true";

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

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);
		}
		return false;
	}

	public static double accountHolderConditions(Transaction trans, Connection con) throws TransactionDaoException {

		String query = "SELECT acc_no,avl_balance FROM account WHERE acc_no = ? AND is_active = true AND avl_balance >= ?";

		double avlBalance = INITIALIZE_ZERO;

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, trans.getAccountHolderAccNo());
			pst.setDouble(2, trans.getTransfer_amount());

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {

					avlBalance = rs.getDouble("avl_balance") - trans.getTransfer_amount();
					System.out.println("accountHolderConditions true");
//						remittanceAccountConditions(trans);

				}
			}

		}

		catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return avlBalance;

	}

	public static double remittanceAccountConditions(Transaction trans, Connection con) throws TransactionDaoException {

		String query = "SELECT acc_no,avl_balance FROM account WHERE acc_no = ? AND ifsc = ? AND is_active = true";

		double avlBalance = INITIALIZE_ZERO;

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, trans.getRemittanceAccNo());
			pst.setString(2, trans.getReceiverIfscCode());

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {

					avlBalance = rs.getDouble("avl_balance") + trans.getTransfer_amount();
					System.out.println("remittanceAccountConditions true");
				}
			}

		} catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return avlBalance;

	}

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

				System.out.println("updateHolderAccount true");
			}
		}

		catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return true;

	}

	public static boolean updateRemittanceAccount(Transaction trans, Connection con) throws TransactionDaoException {

		String query = "UPDATE account SET avl_balance = ? WHERE acc_no = ?";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setDouble(1, remittanceBalance);
			pst.setString(2, trans.getRemittanceAccNo());
			pst.executeUpdate();
			insertAccountHolderDetails(trans, con);
			System.out.println("updateRemittanceAccount true");

		} catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}
		return true;
	}

	public static boolean insertAccountHolderDetails(Transaction trans, Connection con) throws TransactionDaoException {

		String query = "INSERT INTO transaction (acc_holder,remittance,trans_status,trans_amount,avl_balance,remark ) VALUES (? , ? , ? , ? , ? , ? )";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, trans.getAccountHolderAccNo());
			pst.setString(2, trans.getRemittanceAccNo());
			pst.setString(3, "credited");
			pst.setDouble(4, trans.getTransfer_amount());
			pst.setDouble(5, holderBalance);
			pst.setString(6, trans.getRemark());
//			System.out.println(pst);
			pst.executeUpdate();
			System.out.println("insertAccountHolderDetails true");
			insertRemittanceAccountDetails(trans, con);

		}

		catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return true;
	}

	public static boolean insertRemittanceAccountDetails(Transaction trans, Connection con)
			throws TransactionDaoException {

		String query = "INSERT INTO transaction (acc_holder,remittance,trans_status,trans_amount,avl_balance,remark ) VALUES (? , ? , ? , ? , ? , ? )";

		try (PreparedStatement pst = con.prepareStatement(query)) {

			pst.setString(1, trans.getRemittanceAccNo());
			pst.setString(2, trans.getAccountHolderAccNo());
			pst.setString(3, "debited");
			pst.setDouble(4, trans.getTransfer_amount());
			pst.setDouble(5, remittanceBalance);
			pst.setString(6, trans.getRemark());
			pst.executeUpdate();
			System.out.println("insertRemittanceAccountDetails true");
			System.out.println("Check DataBase");

		}

		catch (SQLException e) {

			throw new TransactionDaoException(TransactionDaoErrors.INVALID_ACCOUNT_NUMBER);

		}

		return true;

	}

	public static List<Object> listTransaction(String accNo) throws SQLException {

		ArrayList list = new ArrayList();

		String query = "SELECT * FROM transaction WHERE acc_holder = ?  OR remittance = ? ";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, accNo);
				pst.setString(2, accNo);

				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						Transaction trans = new Transaction();
						
						trans.setAccountHolderAccNo(rs.getString("acc_holder"));
						trans.setRemittanceAccNo(rs.getString("remittance"));
						trans.setTransfer_amount(rs.getDouble("trans_amount"));
						trans.setRemark(rs.getString("remark"));
					
//						rs.getString("trans_status"));
//						rs.getDouble("trans_amount"));
//					rs.getDouble("avl_balance"));
//						rs.getTimestamp("paid_time"));
//					rs.getTimestamp("debited_time"));
						list.add(trans);

					}
				}
			}
		}
		return list;
	}

	public static boolean printTransactions(String accNo) throws SQLException {

		List transList = listTransaction(accNo);

		for (Object list : transList) {

			System.out.println(list);
		}

		return true;
	}

//	public static void readAccountDetails(Transaction trans) throws SQLException {
//
//		String query = "SELECT acc_no,avl_balance FROM account WHERE acc_no = ? AND is_active = true";
//
//		try (Connection con = ConnectionUtil.getConnection()) {
//
//			
//
//		}
//	}

//	public static ArrayList holderDetails(Connection con, String query, Transaction trans) throws SQLException {
//
//		ArrayList holder = new ArrayList();
//		double avlBalance = 0;
//
//		try (PreparedStatement pst = con.prepareStatement(query)) {
//
//			pst.setString(1, trans.getAccountHolderAccNo());
//
//			try (ResultSet rs = pst.executeQuery()) {
//
//				boolean exits = false;
//
//				while (rs.next()) {
//					exits = true;
//					if (rs.getDouble("avl_balance") > trans.getTransfer_amount())
//						remittanceDetails(con, query, trans);
//
//					holder.add(rs.getString("acc_no"));
//
//					avlBalance = trans.getTransfer_amount() - rs.getDouble("avl_balance");
//
//					holder.add(avlBalance);
//				}
//
//				if (exits != true) {
//
//					throw new SQLException();
//				}
//
//			}
//
//		}
//
//		return holder;
//
//	}
//
//	public static ArrayList remittanceDetails(Connection con, String query, Transaction trans) throws SQLException {
//
//		ArrayList remittance = new ArrayList();
//
//		double avl_balance = 0;
//		try (PreparedStatement pst = con.prepareStatement(query)) {
//
//			pst.setString(1, trans.getRemittanceAccNo());
//
//			try (ResultSet rs = pst.executeQuery()) {
//
//				boolean exits = false;
//
//				while (rs.next()) {
//
//					exits = true;
//
//					remittance.add(rs.getString("acc_no"));
//
//					avl_balance = rs.getDouble("avl_balance") + trans.getTransfer_amount();
//
//					remittance.add(avl_balance);
//				}
//
//				if (exits != true) {
//
//					throw new SQLException(); 
//				}
//
//			}
//
//		}
//
//		return remittance;
//	}

	public static void main(String[] args) throws TransactionDaoException, SQLException {

		Transaction trans = new Transaction("6987654321123456", "0987654321123456", "IDIB000K132", 10, "bill pay");
//		isActiveAccount("1234567890123456");
		// updateHolderAccount(trans);

		printTransactions("1234567890123456");

	}
}
