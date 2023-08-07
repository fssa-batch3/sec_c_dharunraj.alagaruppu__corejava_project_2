package com.fssa.netbliz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.netbliz.model.Transaction;

public class TransactionDao {

	public static boolean isActiveAccount(String holder) throws SQLException {

		String query = "SELECT acc_no,avl_balance FROM accounts WHERE acc_no = ? AND is_active = true";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, holder);
				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						return true;
					}
				}

			}
		}
		return false;
	}

	public static double accountHolderConditions(Transaction trans) throws SQLException {

		String query = "SELECT acc_no,avl_balance FROM accounts WHERE acc_no = ? AND is_active = true AND avl_balance >= ?";

		double avlBalance = 0;

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, trans.getAccountHolderAccNo());
				pst.setDouble(2, trans.getTransfer_amount());

				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						avlBalance = rs.getDouble("avl_balance") - trans.getTransfer_amount();
						System.out.println("accountHolderConditions true");
						remittanceAccountConditions(trans);
						return avlBalance;
					}
				}

			}

			throw new SQLException("accountHolderConditions");

		}
	}

	public static double remittanceAccountConditions(Transaction trans) throws SQLException {

		String query = "SELECT acc_no,avl_balance FROM accounts WHERE acc_no = ? AND ifsc = ? AND is_active = true";

		double avlBalance = 0;

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, trans.getRemittanceAccNo());
				pst.setString(2, trans.getReceiverIfscCode());

				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						avlBalance = rs.getDouble("avl_balance") + trans.getTransfer_amount();
						System.out.println("remittanceAccountConditions true");
						updateHolderAccount(trans);
						return avlBalance;
					}
				}

			}

			throw new SQLException("remittanceAccountConditions");
		}
	}

	public static boolean updateHolderAccount(Transaction trans) throws SQLException {

		String query = "UPDATE accounts SET avl_balance = ? WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setDouble(1, accountHolderConditions(trans));
				pst.setString(2, trans.getAccountHolderAccNo());
				pst.executeQuery();
				System.out.println("updateHolderAccount true");
				updateRemittanceAccount(trans);
				return true;
			}
		}

		catch (SQLException e) {

			throw new SQLException("updateHolderAccount");
		}

	}

	public static boolean updateRemittanceAccount(Transaction trans) throws SQLException {

		String query = "UPDATE accounts SET avl_balance = ? WHERE acc_no = ?";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setDouble(1, remittanceAccountConditions(trans));
				pst.setString(2, trans.getRemittanceAccNo());
				pst.executeQuery();
				System.out.println("updateRemittanceAccount true");
				insertAccountHolderDetails(trans);
				return true;
			}
		} catch (SQLException e) {
			throw new SQLException("updateRemittanceAccount");
		}

	}

	public static boolean insertAccountHolderDetails(Transaction trans) throws SQLException {

		String query = "INSERT INTO transactions VALUE(? , ? , ? , ? , ? , ? )";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, trans.getAccountHolderAccNo());
				pst.setString(2, trans.getRemittanceAccNo());
				pst.setString(3, "credited");
				pst.setDouble(4, trans.getTransfer_amount());
				pst.setDouble(5, accountHolderConditions(trans));
				pst.setString(6, trans.getRemark());
				pst.executeUpdate();
				System.out.println("insertAccountHolderDetails true");
				insertRemittanceAccountDetails(trans);
				return true;

			}

		} catch (Exception e) {

			throw new SQLException("insertAccountHolderDetails");
		}

	}
	
	public static boolean insertRemittanceAccountDetails(Transaction trans) throws SQLException {

		String query = "INSERT INTO transactions VALUE(? , ? , ? , ? , ? , ? )";

		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = con.prepareStatement(query)) {

				pst.setString(1, trans.getRemittanceAccNo());
				pst.setString(2, trans.getAccountHolderAccNo()); // 
				pst.setString(3, "debited");
				pst.setDouble(4, trans.getTransfer_amount());
				pst.setDouble(5, remittanceAccountConditions(trans));
				pst.setString(6, trans.getRemark());
				pst.executeUpdate();
				System.out.println("insertRemittanceAccountDetails true");
				System.out.println("Check DataBase");
				return true;

			}

		} catch (Exception e) {

			throw new SQLException("insertRemittanceAccountDetails");
		}

	}

//	public static void readAccountDetails(Transaction trans) throws SQLException {
//
//		String query = "SELECT acc_no,avl_balance FROM accounts WHERE acc_no = ? AND is_active = true";
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

	public static void main(String[] args) throws SQLException {

		Transaction trans = new Transaction("1234567890123456", "0987654321123456", "IDIB000K132", 10, "bill pay");

		accountHolderConditions(trans);
		
	}
}
